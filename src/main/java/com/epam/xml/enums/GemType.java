package com.epam.xml.enums;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "gem-type")
@XmlEnum
public enum GemType {
    @XmlEnumValue("Natural")
    NATURAL,
    @XmlEnumValue("Synthetic")
    SYNTHETIC,
    @XmlEnumValue("Imitation")
    IMITATION
}
