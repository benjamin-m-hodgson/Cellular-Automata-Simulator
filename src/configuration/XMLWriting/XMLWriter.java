package configuration.XMLWriting;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import configuration.XMLParsing.XMLDataFactory;
import configuration.XMLParsing.XMLException;
import simulation.FileController;
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
 * Writes XML Files from grid
 * 
 * @author Katherine Van Dyk
 *
 */
public class XMLWriter extends FileController {

	private final DocumentBuilder DOCUMENT_BUILDER;
	private static String SIMULATION = "simulation";
	private final GridtoXMLConverter CONVERTER = new GridtoXMLConverter();
	private final XMLDataFactory XMLDATA_FACTORY = new XMLDataFactory();
	private List<String> PARAM_DATA_FIELDS;
	private Document DOCUMENT;
	protected static final List<String> STD_DATA_FIELDS = Arrays.asList(new String[] {
			"simulation",
			"type", 
			"format",
			"name",
			"sizeX",
			"sizeY",
			"cell",
	});

	/**
	 * Create a parser for XML files of given type.
	 */
	public XMLWriter () {
		DOCUMENT_BUILDER = getDocumentBuilder();
		DOCUMENT =  DOCUMENT_BUILDER.newDocument();
	}

	/**
	 * Creates document from given grid, ruleset
	 * 
	 * @param g
	 * @param r
	 * @throws TransformerConfigurationException 
	 */
	public void createDoc(String simType, String simName, Grid GRID, Ruleset RULES) throws TransformerConfigurationException {
		PARAM_DATA_FIELDS = XMLDATA_FACTORY.getParameters(simType);
		Element rootElement = DOCUMENT.createElement(SIMULATION);
		DOCUMENT.appendChild(rootElement);
		addStandardElements(simType, simName, rootElement, GRID);
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
	private void addStandardElements(String simType, String simName, Element rootElement, Grid GRID) {
		addAttribute(STD_DATA_FIELDS.get(0),"CA", rootElement);
		addAttribute(STD_DATA_FIELDS.get(1), simType, rootElement);
		addAttribute(STD_DATA_FIELDS.get(2), "locations", rootElement);
		addElement(STD_DATA_FIELDS.get(3), simName, rootElement);
		addElement(STD_DATA_FIELDS.get(4),Integer.toString(GRID.getXSize()), rootElement);
		addElement(STD_DATA_FIELDS.get(5),Integer.toString(GRID.getYSize()), rootElement);
		addElement(STD_DATA_FIELDS.get(6), CONVERTER.cellStates(GRID), rootElement);
	}
	
	/**
	 * Adds simulation-specific (parameter) elements to the XML file
	 * 
	 * @param simType
	 * @param rootElement
	 * @param r
	 */
	private void addParameterElements(String simType, Element rootElement, Ruleset r) {
		List<String> params = CONVERTER.rulesetParam(simType, r, rootElement);
		for(int i = 0; i < PARAM_DATA_FIELDS.size(); i++) {
			addElement(PARAM_DATA_FIELDS.get(i), params.get(i), rootElement);
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
		e.appendChild(DOCUMENT.createTextNode(value));
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