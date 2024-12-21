package com.example.house_backed.mapper;

import com.example.house_backed.pojo.Student;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentMapper {


    /**
     * 根据id获取学生信息
     * @param id id
     * @return 学生对象
     */
    Student getStudentById(String id);

}
