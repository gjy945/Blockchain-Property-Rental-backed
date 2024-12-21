package com.example.house_backed.service;


import com.example.house_backed.pojo.Student;
import org.springframework.stereotype.Service;


public interface IStudentService {

    // 获取学生信息根据id
    Student getStudentById(String id);
}
