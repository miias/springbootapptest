package com.example.test2.spring_test.Repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.test2.spring_test.Models.classes.FlattenedRecord;

public interface FlattenedRecordRepository extends MongoRepository<FlattenedRecord, String> {

    Optional<FlattenedRecord> findByKey(String key);

}
