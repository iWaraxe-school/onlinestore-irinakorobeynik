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
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class XMLParser {
    private final Map<String, SortingTypes> sortingOptions = new LinkedHashMap<>();
    public static final String ROOT_TAG = "sort";

    public void parseSortOptions(String filePath) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(filePath);
            NodeList sortList = doc.getElementsByTagName(ROOT_TAG);
            Node p = sortList.item(0);
            Element sort = (Element) p;
            NodeList nameList = sort.getChildNodes();
            IntStream.range(0, nameList.getLength()).forEach(i ->
            {Node n = nameList.item(i);
                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    Element name = (Element) n;
                    this.sortingOptions.put(name.getTagName(), SortingTypes.findByName(name.getTextContent()));
                }
            });
        } catch (ParserConfigurationException | IOException | SAXException e) {
            System.err.println("Error occurred while parsing the config file: " + e.getMessage());
        }
    }

    public Map<String, SortingTypes> getParsedMap() {
        return this.sortingOptions;
    }
}


