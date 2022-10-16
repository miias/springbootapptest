package com.example.test2.spring_test.Models.classes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class FlattenedRecord {
    @Id
    private String id;
    @Indexed(unique = true)
    private String key;
    private List<String> values = new ArrayList<String>();
}
