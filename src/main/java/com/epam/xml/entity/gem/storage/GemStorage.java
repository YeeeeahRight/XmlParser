package com.epam.xml.entity.gem.storage;

import com.epam.xml.entity.gem.AbstractGem;
import com.epam.xml.entity.gem.implementation.PreciousGem;
import com.epam.xml.entity.gem.implementation.SemiPreciousGem;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "gems", namespace = "https://gems.com")
public class GemStorage {
    @XmlElements({
            @XmlElement(name = "precious-gem", namespace = "https://gems.com", type = PreciousGem.class),
            @XmlElement(name = "semi-precious-gem", namespace = "https://gems.com", type = SemiPreciousGem.class)
    })
    private List<AbstractGem> gems = new ArrayList<>();


    public GemStorage() {

    }

    public void addGem(AbstractGem gem) {
        gems.add(gem);
    }

    @XmlTransient
    public List<AbstractGem> getGems() {
        return gems;
    }


    public void addGems(List<AbstractGem> gems) {
        this.gems.addAll(gems);
    }

    @Override
    public String toString() {
        return "GemStorage{gems=" + gems + '}';
    }
}
