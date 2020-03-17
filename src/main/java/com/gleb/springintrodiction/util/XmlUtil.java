package com.gleb.springintrodiction.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.util.List;

public final class XmlUtil {

    private XmlUtil() {
    }

    public static <K> String convertToXml(List<K> content) {
        XmlMapper xmlMapper = new XmlMapper();
        String xmlContent = null;
        try {
            xmlContent =  xmlMapper.writeValueAsString(content);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return xmlContent;
    }
}