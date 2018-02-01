package configuration;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * This class handles parsing XML files and returning a completed object.
 *
 * @author Katherine Van Dyk, adopted from Robert Duvall
 */
public class XMLParser {
    public static final String ERROR_MESSAGE = "XML file does not represent %s";
    private String TYPE_ATTRIBUTE = "type";
    private final DocumentBuilder DOCUMENT_BUILDER;

    
    /**
     * Create a parser for XML files of given type.
     */
    public XMLParser (String type) {
        DOCUMENT_BUILDER = getDocumentBuilder();
        TYPE_ATTRIBUTE = type;
    }


    /**
     * Get the data contained in this XML file as an object
     */
    public Map<String, String> getGrid (File dataFile) {
        Element root = getRootElement(dataFile);
        if (! isValidFile(root, "simulation")) {
            throw new XMLException(ERROR_MESSAGE, "simulation file type");
        }
        // read data associated with the fields given by the object
        Map<String, String> results = new HashMap<>();
        for (String field : XMLData.DATA_FIELDS) {
            results.put(field, getTextValue(root, field));
        }
        return results;
    }


    // Get root element of an XML file
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

    // Returns if this is a valid XML file for the specified object type
    private boolean isValidFile (Element root, String type) {
        return getAttribute(root, TYPE_ATTRIBUTE).equals(type);
    }

    // Get value of Element's attribute
    private String getAttribute (Element e, String attributeName) {
        return e.getAttribute(attributeName);
    }

    // Get value of Element's text
    private String getTextValue (Element e, String tagName) {
        NodeList nodeList = e.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        else {
            // FIXME: empty string or null, is it an error to not find the text value?
            return "";
        }
    }

    // Helper method to do the boilerplate code needed to make a documentBuilder.
    private DocumentBuilder getDocumentBuilder () {
        try {
            return DocumentBuilderFactory.newInstance().newDocumentBuilder();
        }
        catch (ParserConfigurationException e) {
            throw new XMLException(e);
        }
    }
}
