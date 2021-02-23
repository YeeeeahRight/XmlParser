package com.epam.xml.parser;

import com.epam.xml.entity.gem.AbstractGem;
import com.epam.xml.exceptions.ParserException;
import com.epam.xml.parser.implementation.dom.DomGemXmlParser;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(DataProviderRunner.class)
public class DomGemXmlParserTest {
    private final DomGemXmlParser gemDomParser = new DomGemXmlParser();

    @Test
    @UseDataProvider(value = "dataProviderParseXmlFile", location = XmlParserTestDataProvider.class)//given
    public void test(String XmlFilePath, List<AbstractGem> expectedGems) throws ParserException {
        //when
        List<AbstractGem> actualGems = gemDomParser.parse(XmlFilePath);
        //then
        Assert.assertEquals(expectedGems, actualGems);
    }

    @Test(expected = ParserException.class)//then
    @UseDataProvider(value = "dataProviderParseInvalidXmlFile", location = XmlParserTestDataProvider.class)//given
    public void test(String XmlFilePath) throws ParserException {
        //when
        gemDomParser.parse(XmlFilePath);
    }
}
