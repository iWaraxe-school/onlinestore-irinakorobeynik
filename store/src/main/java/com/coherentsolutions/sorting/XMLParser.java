package com.coherentsolutions.sorting;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class XMLParser {
    private Map<String, String> sortingOptions = new HashMap<>();
    public static final String FILE_PATH = "store/src/main/resources/config.xml";
    public static final String ROOT_TAG = "sort";
    public void parseSortOptions() throws ParserConfigurationException, IOException, SAXException {
        Map<String, String> sortingOptions = new HashMap<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(FILE_PATH);
        NodeList sortList = doc.getElementsByTagName(ROOT_TAG);
        Node p = sortList.item(0);
        Element sort = (Element) p;
        NodeList nameList = sort.getChildNodes();
        for (int i = 0; i < nameList.getLength(); i++) {
            Node n = nameList.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                Element name = (Element) n;
                this.sortingOptions.put(name.getTagName(), name.getTextContent());
            }
        }
    }

}
