package com.example.test2.spring_test.Controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.test2.spring_test.Models.classes.User;
import com.example.test2.spring_test.Services.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/user")
@AllArgsConstructor
public class UserController {
    @Autowired
    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> add(@RequestBody User user) {
        return userService.add(user);
    }

    @PatchMapping("{id}")
    public ResponseEntity<User> patch(@PathVariable String id, @RequestBody Map<Object, Object> body) {
        return userService.patch(id, body);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> get(@PathVariable String id) {
        return userService.getById(id);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<User>> get(Pageable pageable) {
        return userService.getPage(pageable);
    }

    @GetMapping("/list")
    public ResponseEntity<List<User>> get() {
        return userService.getList();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        return userService.removeById(id);
    }
}
