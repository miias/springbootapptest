package com.example.test2.spring_test.Models.classes;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.test2.spring_test.Models.enums.Gender;

import lombok.Data;

@Data
@Document
public class User {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;
    @DBRef
    private Address address;
    private LocalDateTime createdAt;

    public User(
            String firstName,
            String lastName,
            String email,
            Gender gender,
            Address address,
            LocalDateTime createdAt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.address = address;
        this.createdAt = createdAt;
    }

}
