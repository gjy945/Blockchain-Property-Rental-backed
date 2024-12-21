package com.example.house_backed;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.house_backed.mapper")
public class HouseBackedApplication {
    public static void main(String[] args) {
        SpringApplication.run(HouseBackedApplication.class, args);
        System.out.println("启动成功");
    }

}
