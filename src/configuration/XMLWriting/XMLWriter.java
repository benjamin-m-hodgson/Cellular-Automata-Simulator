package configuration.XMLWriting;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import configuration.XMLDataFactory;
import configuration.XMLException;
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
 * Use this class to write XML file containing the current grid (with current cell states)
 * and current rule set parameters.
 * 
 * @author Katherine Van Dyk
 * @date 2/4/18
 *
 */
public class XMLWriter extends FileController {
    private final DocumentBuilder DOCUMENT_BUILDER;
    private final GridtoXMLConverter CONVERTER = new GridtoXMLConverter();
    private final XMLDataFactory XMLDATA_FACTORY = new XMLDataFactory();
    private List<String> PARAM_DATA_FIELDS;
    private Document DOCUMENT;
    private final String LOCATIONS = "locations";
    private final String SIM_TYPE = "CA";
    protected static final List<String> DATA_FIELDS = Arrays.asList(new String[] {
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
	Element rootElement = DOCUMENT.createElement(DATA_FIELDS.get(0));
	DOCUMENT.appendChild(rootElement);
	addStandardElements(simType, simName, rootElement, GRID);
	addParameterElements(simType, rootElement, RULES);
	convertXML(DOCUMENT, simName);
    }

    /**
     * Adds grid elements to XML file (not simulation-specific)
     * 
     * @param simType: Simulation type attribute
     * @param rootElement: root element to add attributes/child elements
     * @param GRID: Grid object returned from engine
     */
    private void addStandardElements(String simType, String simName, Element rootElement, Grid grid) {
	addAttribute(DATA_FIELDS.get(0), SIM_TYPE, rootElement);
	addAttribute(DATA_FIELDS.get(1), simType, rootElement);
	addAttribute(DATA_FIELDS.get(2), LOCATIONS, rootElement);
	addElement(DATA_FIELDS.get(3), simName, rootElement);
	addElement(DATA_FIELDS.get(4),Integer.toString(grid.getXSize()), rootElement);
	addElement(DATA_FIELDS.get(5),Integer.toString(grid.getYSize()), rootElement);
	addElement(DATA_FIELDS.get(6), CONVERTER.cellStates(grid), rootElement);
    }

    /**
     * Adds simulation-specific (parameter) elements to the XML file
     * 
     * @param simType: Simulation type
     * @param rootElement: root element to attach child elements
     * @param r: rule set object returned from game engine
     */
    private void addParameterElements(String simType, Element rootElement, Ruleset r) {
	List<String> params = CONVERTER.rulesetParam(simType, r);
	for(int i = 0; i < PARAM_DATA_FIELDS.size(); i++) {
	    addElement(PARAM_DATA_FIELDS.get(i), params.get(i), rootElement);
	}
    }

    /**
     * Create element with input tag and value, make child element of parent
     * 
     * @param tag: tag of new element
     * @param value: value of new element
     * @param parent: parent element to attach child to
     */
    protected void addElement(String tag, String value, Element parent) {
	Element e = DOCUMENT.createElement(tag);
	e.appendChild(DOCUMENT.createTextNode(value));
	parent.appendChild(e);
    }

    /**
     * Add attribute with desired tag and value to element
     * 
     * @param tag: attribute tag
     * @param value: attribute value
     * @param element: Element to add attribute to
     */
    private void addAttribute(String tag, String value, Element element) {
	Attr attr = DOCUMENT.createAttribute(tag);
	attr.setValue(value);
	element.setAttributeNode(attr);
    }

    /**
     * Converts document to XML file
     * Source code for converting set of nodes to XML document:
     * https://www.mkyong.com/java/how-to-create-xml-file-in-java-dom/
     * 
     * @param doc: input document with nodes
     * @param simName: Simulation name (to make as file name)
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
	    throw new XMLException(e,"Cannot convert XML format to document");
	}
    }

    /**
     * Helper method to do the boilerplate code needed to make a documentBuilder.
     * 
     * @return Document builder
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