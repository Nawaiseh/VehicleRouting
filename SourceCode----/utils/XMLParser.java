package utils;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class XMLParser {
    // <editor-fold defaultstate="collapsed" desc="~~~~~~~~~~~~~~~    File Type Enumeration      ~~~~~~~~~~~~~~~">

    public enum FileType {

        SingleFile, MultipleFiles
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="~~~~~~~~~~~~~~~             Paths             ~~~~~~~~~~~~~~~">
    public String NetworkRootPath;
    public String InputFolder = "Input";
    public String OutputFolder = "Output";

    ;
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="~~~~~~~~~~~~~~~  Private XML Common Methods   ~~~~~~~~~~~~~~~">

    public String GetNodeValue(Element element, String TagName) {
        NodeList NodeListTag = element.getElementsByTagName(TagName);
        Element TagElement = (Element) NodeListTag.item(0);
        NodeList nodeList = TagElement.getChildNodes();
        if (nodeList.getLength() == 0) {
            return "";
        }
        String result = ((Node) nodeList.item(0)).getNodeValue().trim();
        return result;
    }
    public String GetNodeValue(Element Element) {
        NodeList nodeList = Element.getChildNodes();
        if (nodeList.getLength() == 0) {
            return "";
        }
        String result = ((Node) nodeList.item(0)).getNodeValue().trim();
        return result;
    }

    public Element GetNode(Element element, String TagName) {
        NodeList NodeListTag = element.getElementsByTagName(TagName);
        Element TagElement = (Element) NodeListTag.item(0);
        return TagElement;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="~~~~~~~~~~~~~~~ DocumentBuilder Error Handler ~~~~~~~~~~~~~~~">
    public static ErrorHandler DocumentBuilderErrorHandler = new ErrorHandler() {
        @Override
        public void warning(SAXParseException exception) throws SAXException {
        }

        @Override
        public void error(SAXParseException exception) throws SAXException {
        }

        @Override
        public void fatalError(SAXParseException exception) throws SAXException {
        }
    };
    // </editor-fold>

    public XMLParser(String newNetworkRootPath) {
        NetworkRootPath = newNetworkRootPath;
        InputFolder = String.format("%s\\%s", NetworkRootPath, InputFolder);
        OutputFolder = String.format("%s\\%s", NetworkRootPath, OutputFolder);

    }
}
