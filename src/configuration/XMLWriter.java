package configuration;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import simulation.FileController;
import simulation.grid.Grid;
import simulation.ruleSet.Ruleset;
import java.io.File;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Creates XML Files
 * 
 * @author Katherine Van Dyk
 *
 */
public class XMLWriter extends FileController {

	private final DocumentBuilder DOCUMENT_BUILDER;
	private static String SIMULATION = "simulation";
	private final XMLDataFactory XMLDATA_FACTORY;
	private List<String> DATA_FIELDS;
	private Document DOCUMENT;

	/**
	 * Create a parser for XML files of given type.
	 */
	public XMLWriter () {
		DOCUMENT_BUILDER = getDocumentBuilder();
		DOCUMENT =  DOCUMENT_BUILDER.newDocument();
		XMLDATA_FACTORY = new XMLDataFactory();
	}

	/**
	 * Creates document from given grid, ruleset
	 * 
	 * @param g
	 * @param r
	 * @throws TransformerConfigurationException 
	 */
	public void createDoc(String simType, String simName, Grid GRID, Ruleset RULES) throws TransformerConfigurationException {
		DATA_FIELDS = XMLDATA_FACTORY.getDataFields(simType);
		Element rootElement = DOCUMENT.createElement(SIMULATION);
		DOCUMENT.appendChild(rootElement);
		
		addStandardElements(simType, rootElement, GRID);
		addCellStates(rootElement, GRID);
		addParameterElements(simType, rootElement, RULES);
		
		convertXML(DOCUMENT, simName);
	}
	
	/**
	 * Adds grid elements to XML File
	 * 
	 * @param simType
	 * @param rootElement
	 * @param GRID
	 */
	private void addStandardElements(String simType, Element rootElement, Grid GRID) {
		addAttribute(DATA_FIELDS.get(0),"CA", rootElement);
		addAttribute(DATA_FIELDS.get(1), simType, rootElement);
		addElement(DATA_FIELDS.get(2),Integer.toString(GRID.getXSize()), rootElement);
		addElement(DATA_FIELDS.get(3),Integer.toString(GRID.getYSize()), rootElement);
		addElement(DATA_FIELDS.get(4), cellStates(GRID), rootElement);
	}
	
	/**
	 * Adds cell states to XML file
	 * 
	 * @param rootElement
	 * @param GRID
	 */
	private void addCellStates(Element rootElement, Grid GRID) {
		Element cell = DOCUMENT.createElement(DATA_FIELDS.get(4));
		addAttribute("type", "locations", cell);
		rootElement.appendChild(DOCUMENT.createTextNode(cellStates(GRID)));
		rootElement.appendChild(cell);
		addElement(DATA_FIELDS.get(4), cellStates(GRID), rootElement);
	}
	
	/**
	 * Adds simulation-specific (parameter) elements to the XML file
	 * 
	 * @param simType
	 * @param rootElement
	 * @param r
	 */
	private void addParameterElements(String simType, Element rootElement, Ruleset r) {
		List<String> params = XMLDATA_FACTORY.rulesetParam(simType, r, rootElement);
		int count = 0;
		for(int i = 5; i < DATA_FIELDS.size(); i++) {
			addElement(DATA_FIELDS.get(i), params.get(count), rootElement);
			count++;
		}
	}
		
	/**
	 * Add child element to parent
	 * 
	 * @param tag
	 * @param value
	 * @param parent
	 */
	protected void addElement(String tag, String value, Element parent) {
		Element e = DOCUMENT.createElement(tag);
		parent.appendChild(DOCUMENT.createTextNode(value));
		parent.appendChild(e);
	}
	
	/**
	 * Add attribute to element
	 * 
	 * @param tag
	 * @param value
	 * @param parent
	 */
	private void addAttribute(String tag, String value, Element element) {
		Attr attr = DOCUMENT.createAttribute(tag);
		attr.setValue(value);
		element.setAttributeNode(attr);
	}
	
	/**
	 * Converts document to XML file
	 * SOURCE: https://www.mkyong.com/java/how-to-create-xml-file-in-java-dom/
	 * 
	 * @param doc
	 * @throws TransformerConfigurationException
	 */
	private void convertXML(Document doc, String simName) throws TransformerConfigurationException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(FILEPATH + simName + EXTENSION));
		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Returns string representing cell states
	 * @param g
	 * @return
	 */
	private String cellStates(Grid g) {
		StringBuilder result = new StringBuilder();
		for(int r = 0; r < g.getXSize(); r++) {
			StringBuilder element = new StringBuilder();
			for(int c = 0; c < g.getYSize(); c++) {
				element.append(element.toString() 
						+ Integer.toString(g.getCell(r, c).getState()) + " ");
			}
			result.append(element.toString() + "%n");
		}
		return result.toString();
	}

	/**
	 * Helper method to do the boilerplate code needed to make a documentBuilder.
	 * 
	 * @return
	 */
	private DocumentBuilder getDocumentBuilder () {
		try {
			return DocumentBuilderFactory.newInstance().newDocumentBuilder();
		}
		catch (ParserConfigurationException e) {
			throw new XMLException(e);
		}
	}
}