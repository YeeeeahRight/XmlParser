package com.epam.xml.parser.implementation.dom;

import com.epam.xml.entity.gem.AbstractGem;
import com.epam.xml.entity.gem.implementation.PreciousGem;
import com.epam.xml.entity.gem.implementation.SemiPreciousGem;
import com.epam.xml.entity.gem.storage.GemStorage;
import com.epam.xml.enums.GemType;
import com.epam.xml.exceptions.ParserException;
import com.epam.xml.parser.GemXmlParser;
import org.apache.log4j.Logger;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DomGemXmlParser implements GemXmlParser {
    private static final Logger LOGGER = Logger.getLogger(DomGemXmlParser.class);

    @Override
    public List<AbstractGem> parse(String XMLFilePath) throws ParserException {
        LOGGER.info("Start parsing XML file...");
        GemStorage gemStorage;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        File XmlFile = new File(XMLFilePath);
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(XmlFile);
            LOGGER.info("XML file was parsed!");
            gemStorage = buildGems(document);
        } catch (IOException | ParserConfigurationException | SAXException e) {
            throw new ParserException(e.getMessage(), e);
        }
        List<AbstractGem> gemsList = gemStorage.getGems();
        LOGGER.info("Parsed gems: " + gemsList);
        LOGGER.info("GemStorage was parsed!");
        return gemsList;
    }

    private GemStorage buildGems(Document document) throws SAXException {
        Element root = document.getDocumentElement();
        NodeList preciousGemElements = root.getElementsByTagName(PRECIOUS_GEM);
        NodeList semiPreciousGemElements = root.getElementsByTagName(SEMI_PRECIOUS_GEM);
        GemStorage gems = new GemStorage();
        gems.addGems(buildSpecificGems(preciousGemElements));
        gems.addGems(buildSpecificGems(semiPreciousGemElements));
        return gems;
    }

    private List<AbstractGem> buildSpecificGems(NodeList gemElements) throws SAXException {
        GemStorage specificGemStorage = new GemStorage();
        for (int i = 0; i < gemElements.getLength(); i++) {
            Element gemElement = (Element) gemElements.item(i);

            String id = gemElement.getAttribute(ELEMENT_ID);
            if (id == null || id.length() < 4) {
                throw new SAXException("Id is not found.");
            }

            String name = getElementContent(gemElement, ELEMENT_NAME);

            String origin = getElementContent(gemElement, ELEMENT_ORIGIN);

            String hardnessString = getElementContent(gemElement, ELEMENT_HARDNESS);
            double hardness = Double.parseDouble(hardnessString);

            String color = getElementContent(gemElement, ELEMENT_COLOR);

            String gemTypeString = getElementContent(gemElement, ELEMENT_GEM_TYPE);
            GemType gemType = GemType.valueOf(gemTypeString.toUpperCase());

            AbstractGem abstractGem;
            String gemName = gemElement.getTagName();
            if (PRECIOUS_GEM.equals(gemName)) {
                String caratWeightString = getElementContent(gemElement, ELEMENT_CARAT_WEIGHT);
                double caratWeight = Double.parseDouble(caratWeightString);

                abstractGem = new PreciousGem(name, origin, hardness, color, gemType, caratWeight, id);
            } else {
                String weightString = getElementContent(gemElement, ELEMENT_WEIGHT);
                double weight = Double.parseDouble(weightString);

                abstractGem = new SemiPreciousGem(name, origin, hardness, color, gemType, weight, id);
            }
            LOGGER.info("Created gem: " + abstractGem);
            specificGemStorage.addGem(abstractGem);
        }
        return specificGemStorage.getGems();
    }

    private static String getElementContent(Element element, String elementName) throws SAXException {
        final int ELEMENT_INDEX = 0;
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(ELEMENT_INDEX);
        if (node == null) {
            throw new SAXException(elementName + " not found.");
        }
        return node.getTextContent();
    }
}
