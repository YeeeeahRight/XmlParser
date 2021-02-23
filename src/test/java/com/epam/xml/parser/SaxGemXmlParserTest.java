package com.epam.xml.parser;

import com.epam.xml.entity.gem.AbstractGem;
import com.epam.xml.exceptions.ParserException;
import com.epam.xml.parser.implementation.sax.SaxGemXmlParser;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(DataProviderRunner.class)
public class SaxGemXmlParserTest {
    private final SaxGemXmlParser gemSaxParser = new SaxGemXmlParser();

    @Test
    @UseDataProvider(value = "dataProviderParseXmlFile", location = XmlParserTestDataProvider.class)//given
    public void test(String XmlFilePath, List<AbstractGem> expectedGems) throws ParserException {
        //when
        List<AbstractGem> actualGems = gemSaxParser.parse(XmlFilePath);
        //then
        Assert.assertEquals(expectedGems, actualGems);
    }

    @Test(expected = ParserException.class)//then
    @UseDataProvider(value = "dataProviderParseInvalidXmlFile", location = XmlParserTestDataProvider.class)//given
    public void test(String XmlFilePath) throws ParserException {
        //when
        gemSaxParser.parse(XmlFilePath);
    }
}
