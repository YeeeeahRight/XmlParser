package com.epam.xml.entity.gem;

import com.epam.xml.entity.gem.implementation.PreciousGem;
import com.epam.xml.entity.gem.implementation.SemiPreciousGem;
import com.epam.xml.enums.GemType;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractGem", propOrder = {
        "name",
        "origin",
        "hardness",
        "color",
        "gemType"
})
@XmlSeeAlso({
        PreciousGem.class,
        SemiPreciousGem.class,
})
public abstract class AbstractGem {
    @XmlElement(namespace = "https://gems.com", required = true)
    private String name;
    @XmlElement(namespace = "https://gems.com", required = true)
    private String origin;
    @XmlElement(namespace = "https://gems.com", required = true)
    private double hardness;
    @XmlElement(namespace = "https://gems.com", required = true)
    private String color;
    @XmlElement(name = "gem-type", namespace = "https://gems.com", required = true)
    private GemType gemType;

    @XmlAttribute(required = true)
    @XmlID
    private String id;

    //for jaxb
    public AbstractGem() {

    }

    public AbstractGem(String id) {
        this.id = id;
    }

    public AbstractGem(String name, String origin, double hardness, String color, GemType gemType, String id) {
        this.name = name;
        this.origin = origin;
        this.hardness = hardness;
        this.color = color;
        this.gemType = gemType;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public double getHardness() {
        return hardness;
    }

    public void setHardness(double hardness) {
        this.hardness = hardness;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public GemType getGemType() {
        return gemType;
    }

    public void setGemType(GemType gemType) {
        this.gemType = gemType;
    }

    @Override
    public String toString() {
        return ", name='" + name + '\'' +
                ", origin='" + origin + '\'' +
                ", hardness=" + hardness +
                ", color='" + color + '\'' +
                ", gem type=" + gemType +
                ", ID=" + id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractGem)) {
            return false;
        }

        AbstractGem that = (AbstractGem) o;

        if (Double.compare(that.getHardness(), getHardness()) != 0) {
            return false;
        }
        if (!id.equals(that.id)) {
            return false;
        }
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) {
            return false;
        }
        if (getOrigin() != null ? !getOrigin().equals(that.getOrigin()) : that.getOrigin() != null) {
            return false;
        }
        if (getColor() != null ? !getColor().equals(that.getColor()) : that.getColor() != null) {
            return false;
        }
        return getGemType() == that.getGemType();
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getOrigin() != null ? getOrigin().hashCode() : 0);
        temp = Double.doubleToLongBits(getHardness());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getColor() != null ? getColor().hashCode() : 0);
        result = 31 * result + (getGemType() != null ? getGemType().hashCode() : 0);
        result = 31 * result + id.hashCode();
        return result;
    }
}
