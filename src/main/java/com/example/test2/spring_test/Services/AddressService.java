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

import com.example.test2.spring_test.Models.classes.Address;
import com.example.test2.spring_test.Repositories.AddressRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AddressService {

    @Autowired
    private final AddressRepository addressRepository;

    public ResponseEntity<Address> add(Address body) {
        try {
            Address res = addressRepository.save(body);

            if (res == null)
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

            return new ResponseEntity<>(res, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Address> getById(String id) {
        try {
            Optional<Address> res = addressRepository.findById(id);

            if (res == null || res.isEmpty())
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(res.get(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Page<Address>> getPage(Pageable pageable) {
        try {
            Page<Address> res = addressRepository.findAll(pageable);

            if (res == null || res.isEmpty())
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(res, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Address>> getList() {
        try {
            List<Address> res = addressRepository.findAll();

            if (res == null || res.isEmpty())
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(res, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Address> patch(String id, Map<Object, Object> body) {
        try {
            Optional<Address> res = addressRepository.findById(id);
            if (res == null || res.isEmpty())
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            body.forEach((key, value) -> {
                var field = ReflectionUtils.findRequiredField(Address.class, (String) key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, res.get(), value);
            });
            Address updatedAddress = addressRepository.save(res.get());
            return new ResponseEntity<>(updatedAddress, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // public ResponseEntity<Address> removeValues(String id, List<Object> body) {
    // try {
    // Optional<Address> res = addressRepository.findById(id);
    // if (res == null || res.isEmpty())
    // return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    // body.forEach((key) -> {
    // var field = ReflectionUtils.findRequiredField(Address.class, (String) key);
    // field.setAccessible(true);
    // ReflectionUtils.setField(field, res.get(), null);
    // });
    // Address updatedAddress = addressRepository.save(res.get());
    // return new ResponseEntity<>(updatedAddress, HttpStatus.OK);

    // } catch (Exception e) {
    // return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    // }
    // }

    public ResponseEntity<Void> removeById(String id) {
        try {
            addressRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
