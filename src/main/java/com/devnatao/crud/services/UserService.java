package com.devnatao.crud.services;

import com.devnatao.crud.entities.UserEntity;
import com.devnatao.crud.handlers.exceptions.NotFoundException;
import com.devnatao.crud.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public UserEntity create(UserEntity user) {
        return repository.save(user);
    }

    public void delete(Long id) {
        UserEntity user = repository.findById(id)
                                    .orElseThrow(() -> new NotFoundException("Not found"));
        repository.delete(user);
    }

    public UserEntity update(UserEntity user) {
        UserEntity userResponse = repository.findById(user.getId())
                                            .orElseThrow(() -> new NotFoundException("Not found"));

        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setGender(user.getGender());
        return repository.save(userResponse);
    }


    public UserEntity findById(Long id) {
        return repository.findById(id)
                         .orElseThrow(() -> new NotFoundException("Not found"));
    }

    public List<UserEntity> findAll() {
        return repository.findAll();
    }
}
