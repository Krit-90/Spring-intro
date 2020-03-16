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
    }

    public List getContent() {
        return content;
    }

    public void setContent(List content) {
        this.content = content;
    }
}
