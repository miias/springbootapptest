package com.example.test2.spring_test.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.test2.spring_test.Models.classes.User;

public interface UserRepository extends MongoRepository<User, String> {

}
