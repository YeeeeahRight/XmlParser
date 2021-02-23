package com.epam.xml.parser.implementation.sax;

import com.epam.xml.entity.gem.AbstractGem;
import com.epam.xml.exceptions.ParserException;
import com.epam.xml.parser.GemXmlParser;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class SaxGemXmlParser implements GemXmlParser {
    private static final Logger LOGGER = Logger.getLogger(SaxGemXmlParser.class);

    @Override
    public List<AbstractGem> parse(String XMLFilePath) throws ParserException {
        LOGGER.info("Start parsing XML file...");
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SaxGemXmlHandler xmlHandler = new SaxGemXmlHandler();
        File XMLFile = new File(XMLFilePath);
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(XMLFile, xmlHandler);
        } catch (IOException | SAXException | ParserConfigurationException e) {
            throw new ParserException(e.getMessage(), e);
        }
        LOGGER.info("XML file was parsed!");
        return xmlHandler.getGems();
    }
}
