package com.example.house_backed;

import com.example.house_backed.pojo.Student;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class test {
    public static void main(String[] args) throws JsonProcessingException {

        Student student = new Student("1","张三",18,99);
        ObjectMapper objectMapper = new ObjectMapper();
        String str = objectMapper.writeValueAsString(student);

        System.out.println("序列化结果:  ------->"+ str);

        Student o = objectMapper.readValue(str, Student.class);
        System.out.println(o);
    }
}
