package io.github.mayc0n23.ordermanager.mapper;

import io.github.mayc0n23.ordermanager.model.domain.User;
import io.github.mayc0n23.ordermanager.model.entity.UserEntity;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserMapperTest {

    @Test
    void shouldConvertUserToUserEntity() {
        final User user = new User(1L, "John Doe");

        final UserEntity userEntity = UserMapper.toEntity(user);

        assertEquals(user.getId(), userEntity.getId());
        assertEquals(user.getName(), userEntity.getName());
    }

    @Test
    void shouldConvertUserListToUserEntityList() {
        final User john = new User(1L, "John Doe");
        final User jane = new User(2L, "Jane Doe");

        final List<User> users = List.of(john, jane);

        final List<UserEntity> userEntities = UserMapper.toEntityList(users);

        assertEquals(2, userEntities.size());
        assertEquals(john.getId(), userEntities.get(0).getId());
        assertEquals(john.getName(), userEntities.get(0).getName());
        assertEquals(jane.getId(), userEntities.get(1).getId());
        assertEquals(jane.getName(), userEntities.get(1).getName());
    }

    @Test
    void shouldConvertUserEntityToUser() {
        final UserEntity userEntity = new UserEntity(1L, "John Doe");

        final User user = UserMapper.toDomain(userEntity);

        assertEquals(userEntity.getId(), user.getId());
        assertEquals(userEntity.getName(), user.getName());
    }

}