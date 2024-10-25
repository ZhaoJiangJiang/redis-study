package com.example.redisstudy.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Person {
    private String name;
    private String gender;
    private String hobby;
    private int age;
}
