package io.github.mayc0n23.ordermanager.mapper;

import io.github.mayc0n23.ordermanager.model.domain.User;
import io.github.mayc0n23.ordermanager.model.entity.UserEntity;

import java.util.List;

public class UserMapper {

    public static UserEntity toEntity(User user) {
        return new UserEntity(
                user.getId(),
                user.getName());
    }

    public static List<UserEntity> toEntityList(List<User> users) {
        return users.stream()
                .map(UserMapper::toEntity)
                .toList();
    }

    public static User toDomain(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getName());
    }

}