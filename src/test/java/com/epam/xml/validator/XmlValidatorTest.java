package com.epam.xml.validator;

import org.junit.Assert;
import org.junit.Test;

public class XmlValidatorTest {
    private static final String CORRECT_XML_FILE_PATH = "gems.xml";
    private static final String INCORRECT_XML_FILE_PATH = "gemsIncorrect.xml";
    private static final String NOT_EXIST_FILE_PATH = "garbage.xml";
    private static final String XSD_FILE_PATH = "gems.xsd";
    private XmlValidator validator = new XmlValidator(XSD_FILE_PATH);

    @Test
    public void testIsValidShouldReturnTrueWhenFileIsCorrect() {
        //given
        boolean isValid;
        //when
        isValid = validator.isValid(CORRECT_XML_FILE_PATH);
        //then
        Assert.assertTrue(isValid);
    }

    @Test
    public void testIsValidShouldReturnFalseWhenFileIsIncorrect() {
        //given
        boolean isValid;
        //when
        isValid = validator.isValid(INCORRECT_XML_FILE_PATH);
        //then
        Assert.assertFalse(isValid);
    }

    @Test
    public void testIsValidShouldReturnFalseWhenFileIsNotExist() {
        //given
        boolean isValid;
        //when
        isValid = validator.isValid(NOT_EXIST_FILE_PATH);
        //then
        Assert.assertFalse(isValid);
    }
}
