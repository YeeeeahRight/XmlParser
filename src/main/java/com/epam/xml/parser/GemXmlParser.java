package com.epam.xml.parser;

import com.epam.xml.entity.gem.AbstractGem;
import com.epam.xml.exceptions.ParserException;

import java.util.List;

public interface GemXmlParser {
    String PRECIOUS_GEM = "precious-gem";
    String SEMI_PRECIOUS_GEM = "semi-precious-gem";
    String ELEMENT_NAME = "name";
    String ELEMENT_ORIGIN = "origin";
    String ELEMENT_HARDNESS = "hardness";
    String ELEMENT_COLOR = "color";
    String ELEMENT_GEM_TYPE = "gem-type";
    String ELEMENT_ID = "id";
    String ELEMENT_WEIGHT = "weight";
    String ELEMENT_CARAT_WEIGHT = "carat-weight";

    List<AbstractGem> parse(String XMLFilePath) throws ParserException;
}
