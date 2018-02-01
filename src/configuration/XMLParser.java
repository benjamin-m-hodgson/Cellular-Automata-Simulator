package configuration;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import configuration.datatemplates.*;
import simulation.grid.Grid;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * This class handles parsing XML files and returning a completed object
 *
 * @author Katherine Van Dyk
 */
public class XMLParser {
	public static final String FIRE = "Fire";
	public static final String ERROR_MESSAGE = "XML file does not represent %s";
	private String TYPE_ATTRIBUTE = "type";
	private final DocumentBuilder DOCUMENT_BUILDER;
	private XMLData data;

	
	/**
	 * Create a parser for XML files of given type.
	 */
	public XMLParser () {
		data = new XMLData();
		DOCUMENT_BUILDER = getDocumentBuilder();
	}
	
	/**
	 * Get data contained in XML file as object
	 * @param dataFile	File to be parsed
	 * @return Map containing field and file data
	 */
	private Map<String, String> getMap (File dataFile) {
		Element root = getRootElement(dataFile);
		if (! isValidFile(root, "sim")) {
			throw new XMLException(ERROR_MESSAGE, "simulation file type");
		}
		Map<String, String> results = new HashMap<>();
		for (String field : XMLData.DATA_FIELDS) {
			results.put(field, getTextValue(root, field));
		}
		return results;
	}

	
	/**
	 * Get grid from XML Data Object
	 * 
	 * @param dataFile
	 * @return
	 */
	public Grid getGrid(File dataFile) {
		data.setMap(getMap(dataFile));
		return data.getGrid();
	}
	

	/**
	 * Get simulation name from XML Data Object
	 * 
	 * @param dataFile
	 * @return String containing simulation name
	 */
	public String getName(File dataFile) {
		data.setMap(getMap(dataFile));
		return data.getName();
	}
	
	public void setSimulationType(File dataFile) {
		String type = getAttribute(getRootElement(dataFile), "type");
		if(type.equals(FIRE)) data = new FireXMLData();
	}


	/**
	 * Returns root element of XML File
	 * 
	 * @param xmlFile
	 * @return
	 */
	private Element getRootElement (File xmlFile) {
		try {
			DOCUMENT_BUILDER.reset();
			Document xmlDocument = DOCUMENT_BUILDER.parse(xmlFile);
			return xmlDocument.getDocumentElement();
		}
		catch (SAXException | IOException e) {
			throw new XMLException(e);
		}
	}


	/**
	 * Returns if file is valid given expected type
	 * 
	 * @param root
	 * @param type
	 * @return boolean true if valid, false otherwise
	 */
	private boolean isValidFile (Element root, String type) {
		return getAttribute(root, TYPE_ATTRIBUTE).equals(type);
	}

	
	/**
	 * Gets attribute within element
	 * 
	 * @param e
	 * @param attributeName
	 * @return String of text contained in attribute
	 */
	private String getAttribute (Element e, String attributeName) {
		return e.getAttribute(attributeName);
	}

	
	/**
	 * Get text within element
	 * 
	 * @param e
	 * @param tagName
	 * @return
	 */
	private String getTextValue (Element e, String tagName) {
		NodeList nodeList = e.getElementsByTagName(tagName);
		if (nodeList != null && nodeList.getLength() > 0) {
			return nodeList.item(0).getTextContent();
		}
		else {
			return "";
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
