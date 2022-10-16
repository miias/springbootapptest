package com.example.test2.spring_test.Models.classes;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Address {
    @Id
    private String id;
    private String addressName;
    private double longitude;
    private double latitude;
}
