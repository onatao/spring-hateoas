package com.devnatao.crud.controllers;

import com.devnatao.crud.entities.UserEntity;
import com.devnatao.crud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<UserEntity> create(@RequestBody UserEntity user) {
        return new ResponseEntity<>(service.create(user), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UserEntity> update(@RequestBody UserEntity user) {
        return new ResponseEntity<>(service.update(user), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> findById(@PathVariable Long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> findAll() {
        List<UserEntity> responseList = service.findAll();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }
}
