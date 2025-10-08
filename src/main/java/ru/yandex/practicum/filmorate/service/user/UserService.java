package ru.yandex.practicum.filmorate.service.user;

import ru.yandex.practicum.filmorate.dto.user.NewUserRequest;
import ru.yandex.practicum.filmorate.dto.user.UpdateUserRequest;
import ru.yandex.practicum.filmorate.dto.user.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();

    UserDto getById(Long id);

    UserDto create(NewUserRequest request);

    UserDto update(UpdateUserRequest request);

    void deleteById(Long id);

    void addFriend(Long userId, Long friendId);

    void removeFriend(Long userId, Long friendId);

    List<UserDto> findFriends(long userId);

    List<UserDto> findAllCommonFriends(long userId1, long userId2);
}
