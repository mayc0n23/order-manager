package io.github.mayc0n23.ordermanager.service;

import io.github.mayc0n23.ordermanager.mapper.UserMapper;
import io.github.mayc0n23.ordermanager.model.domain.User;
import io.github.mayc0n23.ordermanager.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldSaveUsers() {
        final List<User> users = List.of(new User(1L, "John Doe"));

        userService.saveUsers(users);

        verify(userRepository, times(1)).saveAll(UserMapper.toEntityList(users));
    }

}