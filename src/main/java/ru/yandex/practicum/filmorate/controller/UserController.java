package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    private final Map<Integer, User> users = new HashMap<>();

    @GetMapping
    public List<User> findAll() {
        log.info("Получен список пользователей");
        return List.copyOf(users.values());
    }

    @PostMapping
    public User create(@Valid @RequestBody User user) {
        validateUsers(user);
        user.setId(getNextId());
        log.info("Добавлен пользователь: {}", user);
        users.put(user.getId(), user);
        return user;
    }

    @PutMapping
    public User updateUser(@Valid @RequestBody User user) {
        if (!users.containsKey(user.getId())) {
            throw new ValidationException("Пользователь с указанным id не найден");
        }
        validateUsers(user);
        users.put(user.getId(), user);
        log.info("Обновлен пользователь: {}", user);
        return user;
    }

    // вспомогательный метод для генерации идентификатора нового поста
    private int getNextId() {
        int currentMaxId = users.keySet()
                .stream()
                .mapToInt(id -> id)
                .max()
                .orElse(0);
        return ++currentMaxId;
    }

    // вспомогательный метод для проверки валидности фильмов
    private void validateUsers(User user) {
        if (user.getName() == null || user.getName().isBlank()) {
            log.info("Имя пользователя отсутствует, применяется Логин");
            user.setName(user.getLogin());
        }
    }
}
