package com.devnatao.crud.services;

import com.devnatao.crud.entities.UserEntity;
import com.devnatao.crud.handlers.exceptions.NotFoundException;
import com.devnatao.crud.mapper.DozerMapper;
import com.devnatao.crud.repositories.UserRepository;
import com.devnatao.crud.shared.UserDTO;
import com.devnatao.crud.shared.model.UserRequest;
import com.devnatao.crud.shared.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public UserDTO create(UserDTO user) {
        user.setId(null);

        UserEntity entity = DozerMapper.parseObject(user, UserEntity.class);
        repository.save(entity);

        user.setId(entity.getId());
        return user;
    }

    public void delete(Long id) {
        UserEntity user = repository.findById(id)
                                    .orElseThrow(() -> new NotFoundException("Not found"));
        repository.delete(user);
    }

    public UserDTO update(UserDTO user, Long id) {
        user.setId(id);

        UserEntity entity = DozerMapper.parseObject(user, UserEntity.class);
        repository.save(entity);

        return DozerMapper.parseObject(entity, UserDTO.class);
    }


    public UserDTO findById(Long id) {
        Optional<UserEntity> entity = repository.findById(id);

        return DozerMapper.parseObject(entity.get(), UserDTO.class);
    }

    public List<UserDTO> findAll() {
        List<UserEntity> entityList = repository.findAll();

        return DozerMapper.parseListObjects(entityList, UserDTO.class);
    }
}
