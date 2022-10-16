package com.example.test2.spring_test.Models.classes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class FlattenedJson {
    @Id
    private String id;
    @DBRef
    private List<FlattenedRecord> records = new ArrayList<FlattenedRecord>();
}
