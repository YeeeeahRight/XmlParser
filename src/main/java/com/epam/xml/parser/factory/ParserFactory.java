package com.epam.xml.parser.factory;

import com.epam.xml.enums.ParserType;
import com.epam.xml.parser.GemXmlParser;
import com.epam.xml.parser.implementation.dom.DomGemXmlParser;
import com.epam.xml.parser.implementation.jaxb.JaxbGemXmlParser;
import com.epam.xml.parser.implementation.sax.SaxGemXmlParser;
import org.apache.log4j.Logger;

public class ParserFactory {
    private static final String UNKNOWN_PARSER_TYPE_MESSAGE = "Unknown parser type.";
    private static final Logger LOGGER = Logger.getLogger(ParserType.class);

    public GemXmlParser createParser(ParserType parserType) {
        LOGGER.info("Creating " + parserType + " parser to parse XML file.");
        switch (parserType) {
            case SAX:
                return new SaxGemXmlParser();
            case DOM:
                return new DomGemXmlParser();
            case JAXB:
                return new JaxbGemXmlParser();
            default:
                throw new IllegalArgumentException(UNKNOWN_PARSER_TYPE_MESSAGE);
        }
    }
}
