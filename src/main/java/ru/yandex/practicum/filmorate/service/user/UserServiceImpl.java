package ru.yandex.practicum.filmorate.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.dto.user.NewUserRequest;
import ru.yandex.practicum.filmorate.dto.user.UpdateUserRequest;
import ru.yandex.practicum.filmorate.dto.user.UserDto;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.mapper.UserMapper;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.repository.friends.FriendsRepository;
import ru.yandex.practicum.filmorate.repository.user.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final FriendsRepository friendsRepository;

    @Override
    public List<UserDto> getAll() {
        return userRepository.getAll()
                .stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getById(Long id) {
        return userRepository.getById(id)
                .map(UserMapper::toUserDto)
                .orElseThrow(NotFoundException.supplier("Пользователь с id " + id + " не найден"));
    }

    @Override
    public UserDto create(NewUserRequest request) {
        if (request.getName() == null) {
            request.setName(request.getLogin());
        }

        User user = UserMapper.mapToUser(request);
        user = userRepository.create(user);
        log.info("Добавлен пользователь: {}", user);

        return UserMapper.toUserDto(user);
    }

    @Override
    public UserDto update(UpdateUserRequest request) {
        User user = userRepository.getById(request.getId())
                .orElseThrow(NotFoundException.supplier("Пользователь с id " + request.getId() + " не найден")
                );

        user = UserMapper.updateUserFields(user, request);
        userRepository.update(user);
        log.info("Обновлен пользователь: {}", user);
        return UserMapper.toUserDto(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
        log.info("Удален пользователь с id {}", id);
    }

    @Override
    public void addFriend(Long userId, Long friendId) {
        throwIfUserNotFound(userId);
        throwIfUserNotFound(friendId);

        friendsRepository.addFriend(userId, friendId);
        log.info("Пользователь {} добавляет в друзья пользователя {}", userId, friendId);
    }

    @Override
    public void removeFriend(Long userId, Long friendId) {
        throwIfUserNotFound(userId);
        throwIfUserNotFound(friendId);

        friendsRepository.removeFriend(userId, friendId);
        log.info("Пользователь {} удалил из друзей пользователя {}", userId, friendId);
    }

    @Override
    public List<UserDto> findFriends(long userId) {
        throwIfUserNotFound(userId);
        return userRepository.findAllFriends(userId)
                .stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> findAllCommonFriends(long userId1, long userId2) {
        throwIfUserNotFound(userId1);
        throwIfUserNotFound(userId2);
        return userRepository.findAllCommonFriends(userId1, userId2)
                .stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

    private void throwIfUserNotFound(long userId) {
        userRepository.getById(userId)
                .orElseThrow(NotFoundException.supplier("Пользователь с id " + userId + " не найден"));
    }
}
