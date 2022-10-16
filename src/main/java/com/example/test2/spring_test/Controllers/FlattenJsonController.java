package com.example.test2.spring_test.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.test2.spring_test.Services.FlattenJsonService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/flatten")
@AllArgsConstructor
public class FlattenJsonController {
    @Autowired
    private final FlattenJsonService flattenJsonService;

    @PostMapping(/* "{email}" */)
    public ResponseEntity<String> flatten(/* @PathVariable String email, */ @RequestBody String body) {
        return flattenJsonService.flatten("hubjustin@gmail.com", body);
    }
}
