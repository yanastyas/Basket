import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XMLSettingsReader {
    final boolean isLoad;
    final String loadFile;
    final String loadFormat;
    final boolean isSave;
    final String saveFile;
    final String saveFormat;
    final boolean isLog;
    final String logFile;

    public XMLSettingsReader(File xmlFile) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);

        Element root = doc.getDocumentElement();
        Element loadSetting = (Element) root.getElementsByTagName("load").item(0);
        Element saveSetting = (Element) root.getElementsByTagName("save").item(0);
        Element logSetting = (Element) root.getElementsByTagName("log").item(0);

        isLoad = Boolean.parseBoolean(loadSetting.getElementsByTagName("enabled").item(0).getTextContent());
        loadFile = loadSetting.getElementsByTagName("fileName").item(0).getTextContent();
        loadFormat = loadSetting.getElementsByTagName("format").item(0).getTextContent();

        isSave = Boolean.parseBoolean(saveSetting.getElementsByTagName("enabled").item(0).getTextContent());
        saveFile = saveSetting.getElementsByTagName("fileName").item(0).getTextContent();
        saveFormat = saveSetting.getElementsByTagName("format").item(0).getTextContent();

        isLog = Boolean.parseBoolean(logSetting.getElementsByTagName("enabled").item(0).getTextContent());
        logFile = logSetting.getElementsByTagName("fileName").item(0).getTextContent();

    }
}
