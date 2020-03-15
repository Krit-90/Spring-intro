package com.gleb.springintrodiction.dto;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlType
public class ContentXml {
    @XmlElementWrapper(name="content", nillable = true)
    private List content;

    public ContentXml() {
        this.content = new ArrayList<>();
/*        list.add(new CarDto("Shelby GT500", 1967));
        list.add(new CarDto("Impala SS", 1967));
        list.add(new CarDto("Pontiac GTO", 1969));
        list.add(new CarDto("Porsche 911 ", 1973));*/
    }

    public List getContent() {
        return content;
    }

    public void setContent(List content) {
        this.content = content;
    }
}
