package com.gleb.springintrodiction.dto;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ContentXml {
    // TODO: Почему static?
    private static List list;

    public ContentXml() {
        list = new ArrayList<>();
        list.add(new CarDto("Shelby GT500", 1967));
        list.add(new CarDto("Impala SS", 1967));
        list.add(new CarDto("Pontiac GTO", 1969));
        list.add(new CarDto("Porsche 911 ", 1973));
    }

    @XmlElement
    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
