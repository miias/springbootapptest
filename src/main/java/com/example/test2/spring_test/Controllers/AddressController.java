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

import com.example.test2.spring_test.Models.classes.Address;
import com.example.test2.spring_test.Services.AddressService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/address")
@AllArgsConstructor
public class AddressController {
    @Autowired
    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<Address> add(@RequestBody Address user) {
        return addressService.add(user);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Address> patch(@PathVariable String id, @RequestBody Map<Object, Object> body) {
        return addressService.patch(id, body);
    }

    // @PatchMapping("/removeValues/{id}")
    // public ResponseEntity<Address> patch(@PathVariable String id, @RequestBody
    // List<Object> body) {
    // return addressService.removeValues(id, body);
    // }

    @GetMapping("{id}")
    public ResponseEntity<Address> get(@PathVariable String id) {
        return addressService.getById(id);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Address>> get(Pageable pageable) {
        return addressService.getPage(pageable);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Address>> get() {
        return addressService.getList();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        return addressService.removeById(id);
    }

    // @GetMapping
    // public String test() {
    // return "Hello";
    // }
}
