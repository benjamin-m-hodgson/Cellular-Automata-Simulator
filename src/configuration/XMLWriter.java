package configuration;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import simulation.grid.Grid;
import simulation.ruleSet.Ruleset;
import java.io.File;
import java.util.Arrays;
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
 * @author katherinevandyk
 *
 */
public class XMLWriter {

	private final DocumentBuilder DOCUMENT_BUILDER;
	private String FILEPATH = "data/"; 
	private String EXTENSION = ".xml";
	private static String SIMULATION = "simulation";
	protected static final List<String> DATA_FIELDS = Arrays.asList(new String[] {
			SIMULATION,
			"type",
			"name",
			"sizeX",
			"sizeY",
			"cell",
			"probCatch"
	});


	/**
	 * Create a parser for XML files of given type.
	 */
	public XMLWriter () {
		DOCUMENT_BUILDER = getDocumentBuilder();
	}

	/**
	 * Creates document from given grid, ruleset
	 * 
	 * @param g
	 * @param r
	 * @throws TransformerConfigurationException 
	 */
	//public void createDoc(String simType, String simName, Grid g, Ruleset r) throws TransformerConfigurationException {
	public void createDoc(String simType, String simName, Grid GRID, Ruleset RULES) throws TransformerConfigurationException {

		// Set root element
		Document doc = DOCUMENT_BUILDER.newDocument();
		Element rootElement = doc.createElement(SIMULATION);
		doc.appendChild(rootElement);

		Attr attr = doc.createAttribute(DATA_FIELDS.get(0));
		attr.setValue("CA");
		rootElement.setAttributeNode(attr);
		
		attr = doc.createAttribute(DATA_FIELDS.get(1));
		attr.setValue(simType);
		rootElement.setAttributeNode(attr);
		
		Element name = doc.createElement(DATA_FIELDS.get(2));
		name.appendChild(doc.createTextNode(simName));
		rootElement.appendChild(name);
		
		Element xSize = doc.createElement(DATA_FIELDS.get(3));
		xSize.appendChild(doc.createTextNode(Integer.toString(GRID.getXSize())));
		rootElement.appendChild(xSize);
		
		Element ySize = doc.createElement(DATA_FIELDS.get(4));
		ySize.appendChild(doc.createTextNode(Integer.toString(GRID.getYSize())));
		rootElement.appendChild(ySize);
		
		Element cells = doc.createElement(DATA_FIELDS.get(5));
		cells.appendChild(doc.createTextNode(cellStates(GRID)));
		rootElement.appendChild(cells);
		
		Element param = doc.createElement(DATA_FIELDS.get(6));
		param.appendChild(doc.createTextNode(Double.toString(.6)));
		rootElement.appendChild(param);
		
		convertXML(doc, simName);
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


