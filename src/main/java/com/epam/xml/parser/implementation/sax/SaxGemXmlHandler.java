package com.epam.xml.parser.implementation.sax;

import com.epam.xml.entity.gem.AbstractGem;
import com.epam.xml.entity.gem.implementation.PreciousGem;
import com.epam.xml.entity.gem.implementation.SemiPreciousGem;
import com.epam.xml.entity.gem.storage.GemStorage;
import com.epam.xml.enums.GemType;
import com.epam.xml.parser.GemXmlParser;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SaxGemXmlHandler extends DefaultHandler {
    private static final Logger LOGGER = Logger.getLogger(SaxGemXmlHandler.class);

    private GemStorage gemStorage = new GemStorage();
    private AbstractGem currentGem = null;
    private String currentElementName = null;

    public List<AbstractGem> getGems() {
        return gemStorage.getGems();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (GemXmlParser.PRECIOUS_GEM.equals(qName)
                || GemXmlParser.SEMI_PRECIOUS_GEM.equals(qName)) {
            createGem(qName, attributes);
        } else {
            currentElementName = qName;
        }
    }

    private void createGem(String gemName, Attributes attributes) throws SAXException {
        String id = attributes.getValue(GemXmlParser.ELEMENT_ID);
        if (id == null || id.length() < 4) {
            throw new SAXException("Id is not found.");
        }
        if (GemXmlParser.PRECIOUS_GEM.equals(gemName)) {
            currentGem = new PreciousGem(id);
        } else {
            currentGem = new SemiPreciousGem(id);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (GemXmlParser.PRECIOUS_GEM.equals(qName)
                || GemXmlParser.SEMI_PRECIOUS_GEM.equals(qName)) {
            gemStorage.addGem(currentGem);
            LOGGER.info("Parsed gem: " + currentGem);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String value = new String(ch, start, length).trim();
        if (!"".equals(value) && currentElementName != null) {
            switch (currentElementName) {
                case GemXmlParser.ELEMENT_NAME:
                    currentGem.setName(value);
                    break;
                case GemXmlParser.ELEMENT_ORIGIN:
                    currentGem.setOrigin(value);
                    break;
                case GemXmlParser.ELEMENT_HARDNESS:
                    double hardness = Double.parseDouble(value);
                    currentGem.setHardness(hardness);
                    break;
                case GemXmlParser.ELEMENT_COLOR:
                    currentGem.setColor(value);
                    break;
                case GemXmlParser.ELEMENT_GEM_TYPE:
                    GemType gemType = GemType.valueOf(value.toUpperCase());
                    currentGem.setGemType(gemType);
                    break;
                case GemXmlParser.ELEMENT_CARAT_WEIGHT:
                    double caratWeight = Double.parseDouble(value);
                    ((PreciousGem) currentGem).setCaratWeight(caratWeight);
                    break;
                case GemXmlParser.ELEMENT_WEIGHT:
                    double weight = Double.parseDouble(value);
                    ((SemiPreciousGem) currentGem).setWeight(weight);
                    break;
                default:
                    throw new SAXException("Unknown element name: " + currentElementName);
            }
        }
        currentElementName = null;
    }
}
