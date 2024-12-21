package com.example.house_backed.service.impl;

import com.example.house_backed.mapper.StudentMapper;
import com.example.house_backed.pojo.Student;
import com.example.house_backed.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class StudentServiceImpl implements IStudentService {

    @Resource
    private StudentMapper studentMapper;

    @Override
    public Student getStudentById(String id) {
        return studentMapper.getStudentById(id);
    }
}
