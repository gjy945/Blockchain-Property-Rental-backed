package com.example.house_backed.controller;


import com.example.house_backed.pojo.Student;
import com.example.house_backed.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private IStudentService studentService;

    @GetMapping("/getStudentById")
    public Student getStudent(String id){
       Student student =  studentService.getStudentById(id);
       return student;
    }
}
