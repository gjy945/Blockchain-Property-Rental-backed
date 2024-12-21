package com.example.house_backed.pojo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("学生实体类对象")

public class Student {

    private String id;

    private String name;

    private Integer age;

    private Integer score;
}
