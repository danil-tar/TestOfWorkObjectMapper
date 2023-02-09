package com.dan.dson;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ObjectMapper {
    private String jsonString;

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Peaple peaple = new Peaple();
        peaple.name = "Dan";
        peaple.subName = "Tupolev";

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.createJson(peaple);
        System.out.println(json);
    }

    public String createJson(Object a) throws InvocationTargetException, IllegalAccessException {

        Class<?> aClass = a.getClass();
        Method[] declaredMethods = aClass.getDeclaredMethods();
        HashMap<String, String> map = new HashMap<>();
        for (Method method : declaredMethods) {
            if (method.getName().startsWith("get")) {
                String key = method.getName().substring(3);

                method.setAccessible(true);
                String value = method.invoke(a).toString();

                map.put(key, value);
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\n");
        Set<Map.Entry<String, String>> entrySet = map.entrySet();

        for (Map.Entry<String, String> stringStringEntry : entrySet) {
            stringBuilder.append("\"" + stringStringEntry.getKey() + "\":" + "\""+ stringStringEntry.getValue() + "\","+"\n");
        }
        if (stringBuilder.length() > 2) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 2);
            stringBuilder.append("}");
            jsonString = stringBuilder.toString();
        }else {
            stringBuilder.append("}");
            jsonString = stringBuilder.toString();
        }

        return jsonString;
    }

    public static class Peaple {
        String name;
        String subName;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSubName() {
            return subName;
        }

        public void setSubName(String substring) {
            this.subName = substring;
        }
    }
}

