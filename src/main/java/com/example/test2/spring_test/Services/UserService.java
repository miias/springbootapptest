package com.example.test2.spring_test.Services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.test2.spring_test.Models.classes.User;
import com.example.test2.spring_test.Repositories.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public ResponseEntity<User> add(User body) {
        try {
            User res = userRepository.save(body);

            if (res == null)
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

            return new ResponseEntity<>(res, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<User> getById(String id) {
        try {
            Optional<User> res = userRepository.findById(id);

            if (res == null || res.isEmpty())
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(res.get(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Page<User>> getPage(Pageable pageable) {
        try {
            Page<User> res = userRepository.findAll(pageable);

            if (res == null || res.isEmpty())
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(res, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<User>> getList() {
        try {
            List<User> res = userRepository.findAll();

            if (res == null || res.isEmpty())
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(res, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<User> patch(String id, Map<Object, Object> body) {
        try {
            Optional<User> res = userRepository.findById(id);
            if (res == null || res.isEmpty())
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            body.forEach((key, value) -> {
                var field = ReflectionUtils.findRequiredField(User.class, (String) key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, res.get(), value);
            });
            User updatedUser = userRepository.save(res.get());
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> removeById(String id) {
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
