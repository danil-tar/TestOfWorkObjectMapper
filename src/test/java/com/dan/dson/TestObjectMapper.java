package com.dan.dson;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class TestObjectMapper {
    @Test
    public void testToJson(){
        Peaple peaple = new Peaple();
        peaple.name = "Dan";
        peaple.subName = "Tupolev";

        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.createJson(peaple);
        } catch (SerializationException e) {
           e.printStackTrace();
        }

        Assert.assertEquals("{\n" +
                "\"SubName\":\"Tupolev\",\n" +
                "\"Name\":\"Dan\"\n" +
                "}", json);
    }
}
