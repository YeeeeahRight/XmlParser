package com.epam.xml.entity.gem.implementation;

import com.epam.xml.entity.gem.AbstractGem;
import com.epam.xml.enums.GemType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "precious-gem",  propOrder = {"caratWeight"})
public class PreciousGem extends AbstractGem {
    @XmlElement(name ="carat-weight", namespace = "https://gems.com", required = true)
    private double caratWeight;

    //for jaxb
    public PreciousGem() {

    }

    public PreciousGem(String id) {
        super(id);
    }

    public PreciousGem(String name, String origin, double hardness, String color,
                       GemType gemType, double caratWeight, String id) {
        super(name, origin, hardness, color, gemType, id);
        this.caratWeight = caratWeight;
    }

    public double getCaratWeight() {
        return caratWeight;
    }

    public void setCaratWeight(double caratWeight) {
        this.caratWeight = caratWeight;
    }

    @Override
    public String toString() {
        return "Precious gem{" +
                "carat weight=" + caratWeight +
                super.toString() + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PreciousGem)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        PreciousGem that = (PreciousGem) o;

        if (!(Double.compare(that.getCaratWeight(), getCaratWeight()) == 0)) {
            return false;
        }
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(getCaratWeight());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
