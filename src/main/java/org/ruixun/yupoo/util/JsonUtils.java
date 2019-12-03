package org.ruixun.yupoo.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class JsonUtils {
    private  static ObjectMapper mapper=new ObjectMapper();
    public static  String objectTOJson(Object object) throws JsonProcessingException {

        return mapper.writeValueAsString(object);
    }

    public static <T>T jsonToPoJo(String json,Class<T>pojo) throws IOException {
        return mapper.readValue(json, pojo);
    }
    public static <T>List<T> jsonToList(String json,Class<T>pojo) throws IOException {
        JavaType javaType=mapper.getTypeFactory().constructParametricType(List.class,pojo);
        return  mapper.readValue(json,javaType);
    }
    public static <T>Set<T> jsonToSet(String json,Class<T>pojo) throws IOException {
        JavaType javaType=mapper.getTypeFactory().constructParametricType(Set.class,pojo);
        return  mapper.readValue(json,javaType);
    }

}
