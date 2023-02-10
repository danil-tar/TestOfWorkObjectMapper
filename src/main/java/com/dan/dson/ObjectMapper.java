package com.dan.dson;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ObjectMapper {

    public String createJson(Object a) throws SerializationException {

        Class<?> aClass = a.getClass();
        Method[] declaredMethods = aClass.getDeclaredMethods();
        HashMap<String, String> map = new HashMap<>();
        for (Method method : declaredMethods) {
            if (method.getName().startsWith("get")) {
                String key = method.getName().substring(3);

                method.setAccessible(true);
                String value = null;
                try {
                    value = method.invoke(a).toString();
                } catch (IllegalAccessException | InvocationTargetException e) {
                  throw new SerializationException(e);
                }

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
           return stringBuilder.toString();
        }else {
            stringBuilder.append("}");
            return  stringBuilder.toString();
        }
    }

}

