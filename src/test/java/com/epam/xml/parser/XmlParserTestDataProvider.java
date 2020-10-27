package com.epam.xml.parser;

import com.epam.xml.entity.gem.AbstractGem;
import com.epam.xml.entity.gem.implementation.PreciousGem;
import com.epam.xml.entity.gem.implementation.SemiPreciousGem;
import com.epam.xml.enums.GemType;
import com.tngtech.java.junit.dataprovider.DataProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class XmlParserTestDataProvider {
    private static final String CORRECT_XML_FILE_PATH = "gems.xml";
    private static final String INCORRECT_XML_FILE_PATH = "gemsIncorrect.xml";
    private static final String NOT_EXISTING_FILE_PATH = "garbage.xml";
    private static final PreciousGem FIRST_GEM = new PreciousGem("Diamond", "Australia",
            10.0, "Colorless", GemType.NATURAL, 1.5, "ID-1");
    private static final PreciousGem SECOND_GEM = new PreciousGem("Ruby", "Myanmar",
            9.0, "Red", GemType.SYNTHETIC, 10.32, "ID-2");
    private static final SemiPreciousGem THIRD_GEM = new SemiPreciousGem("Agate", "Georgia",
            6.6, "Black", GemType.NATURAL, 21.0, "ID-3");
    private static final SemiPreciousGem FOURTH_GEM = new SemiPreciousGem("Amethyst", "Sri lanka",
            7.0, "Violet", GemType.IMITATION, 10.0, "ID-4");
    private static final List<AbstractGem> GEMS = new ArrayList<>
            (Arrays.asList(FIRST_GEM, SECOND_GEM, THIRD_GEM, FOURTH_GEM));

    @DataProvider
    public static Object[][] dataProviderParseXmlFile() {
        return new Object[][] {
                {CORRECT_XML_FILE_PATH, GEMS}
        };
    }

    @DataProvider
    public static Object[] dataProviderParseInvalidXmlFile() {
        return new Object[] {INCORRECT_XML_FILE_PATH, NOT_EXISTING_FILE_PATH};
    }
}
