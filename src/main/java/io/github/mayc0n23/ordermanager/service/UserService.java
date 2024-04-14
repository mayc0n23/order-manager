package io.github.mayc0n23.ordermanager.service;

import io.github.mayc0n23.ordermanager.mapper.UserMapper;
import io.github.mayc0n23.ordermanager.model.domain.User;
import io.github.mayc0n23.ordermanager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUsers(List<User> users) {
        final var userEntities = UserMapper.toEntityList(users);
        userRepository.saveAll(userEntities);
    }

}