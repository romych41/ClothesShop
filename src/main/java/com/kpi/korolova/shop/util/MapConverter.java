package com.kpi.korolova.shop.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import java.util.Map;

public class MapConverter implements AttributeConverter<Map<String, Object>, String> {

    @Override
    public String convertToDatabaseColumn(Map<String, Object> stringStringMap) {
        if(stringStringMap == null) {
            return null;
        }
        String result = null;
        try {
            result = new ObjectMapper().writeValueAsString(stringStringMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String s) {
        if(s == null || s.isEmpty()) {
            return null;
        }
        Map<String, Object> result = null;
        try {
            result = new ObjectMapper().readValue(s, Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
