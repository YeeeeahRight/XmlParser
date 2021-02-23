package com.epam.xml.entity.gem.implementation;

import com.epam.xml.entity.gem.AbstractGem;
import com.epam.xml.enums.GemType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "semi-precious-gem", propOrder = "weight")
public class SemiPreciousGem extends AbstractGem {
    @XmlElement(namespace = "https://gems.com", required = true)
    private double weight;

    //for jaxb
    public SemiPreciousGem() {

    }

    public SemiPreciousGem(String id) {
        super(id);
    }

    public SemiPreciousGem(String name, String origin, double hardness, String color,
                           GemType gemType, double weight, String id) {
        super(name, origin, hardness, color, gemType, id);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Semi precious gem{" +
                "carat weight=" + weight +
                super.toString() + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SemiPreciousGem)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        SemiPreciousGem that = (SemiPreciousGem) o;

        if (!(Double.compare(that.getWeight(), getWeight()) == 0)) {
            return false;
        }
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(getWeight());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
