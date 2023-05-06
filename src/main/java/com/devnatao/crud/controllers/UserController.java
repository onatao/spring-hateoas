package com.devnatao.crud.controllers;

import com.devnatao.crud.entities.UserEntity;
import com.devnatao.crud.mapper.DozerMapper;
import com.devnatao.crud.services.UserService;
import com.devnatao.crud.shared.UserDTO;
import com.devnatao.crud.shared.model.UserRequest;
import com.devnatao.crud.shared.model.UserResponse;
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
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest user) {
        UserDTO dto = DozerMapper.parseObject(user, UserDTO.class);
        service.create(dto);
        return new ResponseEntity<>(DozerMapper.parseObject(dto, UserResponse.class), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@RequestBody UserRequest user, @PathVariable Long id) {
        UserDTO dto = DozerMapper.parseObject(user, UserDTO.class);
        service.update(dto, id);
        return new ResponseEntity<>(DozerMapper.parseObject(dto, UserResponse.class), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        return new ResponseEntity<>(DozerMapper.parseObject(service.findById(id), UserResponse.class), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        List<UserDTO> responseList = service.findAll();
        return new ResponseEntity<>(DozerMapper.parseListObjects(responseList, UserResponse.class), HttpStatus.OK);
    }
}
