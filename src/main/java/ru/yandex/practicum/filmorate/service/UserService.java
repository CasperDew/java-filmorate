package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.friends.FriendsStorage;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserStorage userStorage;
    private final FriendsStorage friendsStorage;

    public List<User> getAll() {
        return userStorage.getAll();
    }

    public User getUserById(Long id) {
        return userStorage.getById(id)
                .orElseThrow(() -> new NotFoundException("Пользователь с ID " + id + " не найден"));
    }

    public User create(User user) {
        return userStorage.create(user);
    }

    public User update(User user) {
        return userStorage.update(user);
    }

    public void addFriends(Long userId, Long friendId) {
        getUserById(userId);
        getUserById(friendId);

        friendsStorage.addFriends(userId, friendId);
    }

    public void removeFriends(Long userId, Long friendId) {
        getUserById(userId);
        getUserById(friendId);

        friendsStorage.removeFriends(userId, friendId);
    }

    public List<User> getFriends(Long userId) {
        getUserById(userId);
        Set<Long> friendsId = friendsStorage.getFriends(userId);

        return friendsId.stream()
                .map(this::getUserById)
                .collect(Collectors.toList());
    }

    public List<User> getCommonFriends(Long userId, Long otherId) {
        getUserById(userId);
        getUserById(otherId);
        Set<Long> commonFriendIds = friendsStorage.getCommonFriends(userId, otherId);

        return commonFriendIds.stream()
                .map(this::getUserById)
                .collect(Collectors.toList());
    }
}
