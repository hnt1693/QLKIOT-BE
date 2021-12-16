package com.fil.authentication.commons;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fil.authentication.models.Customer;
import com.fil.authentication.models.Group;
import com.fil.authentication.models.Role;
import org.bouncycastle.asn1.x509.qualified.RFC3739QCObjectIdentifiers;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Utils {

    public static ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }


    public static Map<String, Object> convertDTO(String fields, Object o) {
        Map<String, Object> map = new HashMap<>();
        String[] arrayFields = fields.split(",");
        Class<?> clazz = o.getClass();
        for (String s : arrayFields) {
            try {
                Field field = clazz.getDeclaredField(s);
                field.setAccessible(true);
                Object fieldValue = field.get(o);
                map.put(s, fieldValue);
            } catch (Exception e) {
                map.put(s, null);
            }
        }
        return map;
    }


    public static Map<String, String> getSearchMap(String searchData) {
        Map<String, String> map = new HashMap<>();
        String[] searchArr = searchData.split(",");
        String[] temp;
        for (String s : searchArr) {
            temp = s.split("=");
            if (temp.length == 2) {
                map.put(temp[0], temp[1]);
            }
        }
        return map;
    }

    public static String setAliasForEntity(String alias, String sortData) {
        String[] arr = sortData.split(",");
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : arr) {
            stringBuilder.append(alias).append(".").append(s).append(", ");
        }
        return stringBuilder.substring(0, stringBuilder.toString().length() - 2);
    }

    public static String randomKey(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(random.nextInt(10));
        }
        return stringBuilder.toString();
    }
}
