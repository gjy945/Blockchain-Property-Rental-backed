package com.example.house_backed.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class House {

    private int id;

    private String landlordAddress;

    private boolean isActive;

    private int rent;

    private int deposit;
}
