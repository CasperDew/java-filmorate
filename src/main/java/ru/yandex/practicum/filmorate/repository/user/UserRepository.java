package ru.yandex.practicum.filmorate.repository.user;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> getAll();

    Optional<User> getById(Long id);

    User create(User user);

    void update(User user);

    void deleteById(Long id);

    List<User> findAllFriends(long userId);

    List<User> findAllCommonFriends(long userId1, long userId2);
}
