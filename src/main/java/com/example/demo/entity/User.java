package com.example.demo.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {
    private int id;
    private String name;
    private String email;
}
