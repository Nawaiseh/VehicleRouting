package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class XMLDocumentHandler {
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~    File Type Enumeration      ~~~~~~~~~~~~~~~ ">

    public enum FileType {

        SingleFile, MultipleFiles
    }
    // </editor-fold>
    //<editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~          Time Format          ~~~~~~~~~~~~~~~ ">

    public enum TimeFormat {

        Hours, Minutes, Seconds, HoursAndMinutes, MinuteAndSeconds, HoursMinutesAndSeconds
    }
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~             Paths             ~~~~~~~~~~~~~~~ ">
    public String BasePath;
    public String InputFolder = "Input";
    public String OutputFolder = "Output";

    ;
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~  Private XML Common Methods   ~~~~~~~~~~~~~~~ ">

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
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~ DocumentBuilder Error Handler ~~~~~~~~~~~~~~~ ">
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
    //<editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~         Error Logger          ~~~~~~~~~~~~~~~ ">
    public static final Logger MyLogger = Logger.getLogger(XMLDocumentHandler.class.getName());
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~        Open XML Document        ~~~~~~~~~~~~~~ ">

    protected Document OpenXMLDocument(String FileName) {
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            docBuilder.setErrorHandler(DocumentBuilderErrorHandler);
            Document Document = docBuilder.parse(new File(FileName));
            // normalize text representation
            Document.getDocumentElement().normalize();

            return Document;
        } catch (Exception Exception) {
            MyLogger.log(Level.SEVERE, String.format("Error In Parsing '%s'", FileName), Exception);
            return null;
        }
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~      Get Time In Seconds        ~~~~~~~~~~~~~~ ">

    public int GetTimeInSeconds(String TimeStr, TimeFormat Format) {
        if (Format == TimeFormat.Hours) {
            int Hours = Integer.parseInt(TimeStr);
            int Time = Hours * 3600;
            return Time;
        } else if (Format == TimeFormat.Minutes) {
            int Minutes = Integer.parseInt(TimeStr);
            int Time = Minutes * 60;
            return Time;
        } else if (Format == TimeFormat.Seconds) {
            int Seconds = Integer.parseInt(TimeStr);
            int Time = Seconds;
            return Time;
        } else if (Format == TimeFormat.HoursAndMinutes) {
            StringTokenizer token = new StringTokenizer(TimeStr, ":");
            String hour = token.nextToken();
            int Hours = Integer.parseInt(hour);
            String minute = token.nextToken();
            int Minutes = Integer.parseInt(minute);
            int Time = ((Hours * 3600) + (Minutes * 60));
            return Time;
        } else if (Format == TimeFormat.MinuteAndSeconds) {
            StringTokenizer token = new StringTokenizer(TimeStr, ":");
            String minutes = token.nextToken();
            int Minutes = Integer.parseInt(minutes);
            String seconds = token.nextToken();
            int Seconds = Integer.parseInt(seconds);
            int Time = ((Minutes * 60) + Seconds);
            return Time;
        } else if (Format == TimeFormat.HoursMinutesAndSeconds) {
            StringTokenizer token = new StringTokenizer(TimeStr, ":");
            String hour = token.nextToken();
            int Hours = Integer.parseInt(hour);
            String minutes = token.nextToken();
            int Minutes = Integer.parseInt(minutes);
            String seconds = token.nextToken();
            int Seconds = Integer.parseInt(seconds);
            int Time = ((Hours * 3600) + (Minutes * 60) + Seconds);
            return Time;
        }
        return -1;
    }

    public String SecondsToTime(int Seconds) {
        Seconds = (Seconds % 86400);
        int hour = Seconds / 3600;

        int min = (Seconds - (hour * 3600)) / 60;
        int second = Seconds - ((hour * 3600) + (min * 60));

        String H = "" + ((hour > 9) ? hour : "0" + hour);
        H = (H.length() > 1) ? H : ("0" + H);
        String M = "" + ((min > 9) ? min : "0" + min);
        M = (M.length() > 1) ? M : ("0" + M);
        String S = "" + ((second > 9) ? second : "0" + second);
        S = (S.length() > 1) ? S : ("0" + S);
        return String.format("%s:%s:%s", H, M, S);
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~    Create / Close Data File     ~~~~~~~~~~~~~~ ">

    public XMLStreamWriter CreateDataFile_WriteMode(String MainTag, String FileName) {
        FileName = String.format("%s\\%s", OutputFolder, FileName);

        XMLOutputFactory XML_Output_Factory = XMLOutputFactory.newInstance();
        try {
            FileOutputStream FileOutputStream = new FileOutputStream(FileName);
            XMLStreamWriter XMLStreamWriter = XML_Output_Factory.createXMLStreamWriter(FileOutputStream, "UTF-8");
            XMLStreamWriter.writeStartDocument("UTF-8", "1.0");
            XMLStreamWriter.writeStartElement(MainTag);
            return XMLStreamWriter;

        } catch (Exception Exception) {
            MyLogger.log(Level.SEVERE, String.format("Error In Creating '%s'", FileName), Exception);
        }
        return null;
    }

    public void CloseDataFile_WriteMode(XMLStreamWriter XMLStreamWriter) {
        try {
            XMLStreamWriter.writeEndElement();
            XMLStreamWriter.writeEndDocument();
            XMLStreamWriter.flush();
            XMLStreamWriter.close();
        } catch (Exception Exception) {
        }
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~          Variables            ~~~~~~~~~~~~~~~ ">
    protected DecimalFormat DecimalFormatter = new DecimalFormat("#,##0.00000");
    protected DateFormat DateFormatter = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM);
    protected DateFormat DateFormatterLong = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
    protected DecimalFormat TimeFormatter = new DecimalFormat("00");
    protected TreeMap<String, String> HTMLCodes = new TreeMap();
    protected DistanceConversion DistanceConversion = new DistanceConversion();
    //</editor-fold>

    private void InitializeHTMLCodes() {
        HTMLCodes.put("&quot;", "\"");
        HTMLCodes.put("&amp;", "&");
        HTMLCodes.put("&lt;", "<");
        HTMLCodes.put("&gt;", ">");
        HTMLCodes.put("&OElig;", "Œ");
        HTMLCodes.put("&oelig;", "œ");
        HTMLCodes.put("&Scaron;", "Š");
        HTMLCodes.put("&scaron;", "š");
        HTMLCodes.put("&Yuml;", "Ÿ");
        HTMLCodes.put("&circ;", "ˆ");
        HTMLCodes.put("&tilde;", "˜");
        HTMLCodes.put("&ndash;", "–");
        HTMLCodes.put("&mdash;", "—");
        HTMLCodes.put("&lsquo;", "‘");
        HTMLCodes.put("&rsquo;", "’");
        HTMLCodes.put("&sbquo;", "‚");
        HTMLCodes.put("&rdquo;", "“");
        HTMLCodes.put("&quot;", "”");
        HTMLCodes.put("&bdquo;", "„");
        HTMLCodes.put("&dagger;", "†");
        HTMLCodes.put("&Dagger;", "‡");
        HTMLCodes.put("&permil;", "‰");
        HTMLCodes.put("&lsaquo;", "‹");
        HTMLCodes.put("&rsaquo;", "›");
        HTMLCodes.put("&euro;", "€");
    }

    public XMLDocumentHandler(String newNetworkRootPath) {
        BasePath = newNetworkRootPath;
        InputFolder = String.format("%s\\%s", BasePath, InputFolder);
        OutputFolder = String.format("%s\\%s", BasePath, OutputFolder);
        InitializeHTMLCodes();
    }

    public XMLDocumentHandler() {
        InitializeHTMLCodes();
    }
}
