package com.epam.xml.parser.implementation.jaxb;

import com.epam.xml.entity.gem.AbstractGem;
import com.epam.xml.entity.gem.storage.GemStorage;
import com.epam.xml.exceptions.ParserException;
import com.epam.xml.parser.GemXmlParser;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class JaxbGemXmlParser implements GemXmlParser {
    private static final Logger LOGGER = Logger.getLogger(JaxbGemXmlParser.class);

    @Override
    public List<AbstractGem> parse(String XMLFilePath) throws ParserException {
        LOGGER.info("Starting parsing XML file...");
        FileReader reader = null;
        List<AbstractGem> gemsList;
        try {
            JAXBContext context = JAXBContext.newInstance(GemStorage.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            reader = new FileReader(XMLFilePath);
            GemStorage gemStorageStorage = (GemStorage) unmarshaller.unmarshal(reader);
            LOGGER.info("XML file was parsed!");
            gemsList = gemStorageStorage.getGems();
        } catch (JAXBException | FileNotFoundException e) {
            throw new ParserException(e.getMessage(), e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
        LOGGER.info("Parsed gems: " + gemsList);
        LOGGER.info("GemStorage was parsed!");
        return gemsList;
    }
}
