package com.epam.xml.validator;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlValidator {
    private static final Logger LOGGER = Logger.getLogger(XmlValidator.class);
    private static final String LANGUAGE = XMLConstants.W3C_XML_SCHEMA_NS_URI;
    private final String XMLSchemaFilePath;

    public XmlValidator(String XmlSchemaFilePath) {
        this.XMLSchemaFilePath = XmlSchemaFilePath;
    }

    public boolean isValid(String XmlFilePath) {
        LOGGER.info("Start validating XML file...");
        File XMLFile = new File(XmlFilePath);
        File XSDFile = new File(XMLSchemaFilePath);
        SchemaFactory factory = SchemaFactory.newInstance(LANGUAGE);
        try {
            Schema schema = factory.newSchema(new StreamSource(XSDFile));
            Validator validator = schema.newValidator();
            Source XMLSource = new StreamSource(XMLFile);
            validator.validate(XMLSource);
        } catch (SAXException | IOException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
        LOGGER.info("XML file is valid!");
        return true;
    }
}
