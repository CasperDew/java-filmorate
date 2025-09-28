package ru.yandex.practicum.filmorate.storage.film;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class InMemoryFilmStorage implements FilmStorage {
    private final Map<Integer, Film> films = new HashMap<>();

    @Override
    public List<Film> getAll() {
        return List.copyOf(films.values());
    }

    @Override
    public void getById(Long id) {
        films.get(id);
    }

    @Override
    public Film create(Film film) {
        validateFilms(film);
        film.setId(getNextId());
        films.put(film.getId(), film);
        return film;
    }

    @Override
    public Film update(Film film) {
        if (!films.containsKey(film.getId())) {
            throw new ValidationException("Фильм с указанным id не найден");
        }
        validateFilms(film);
        films.put(film.getId(), film);
        return film;
    }

    @Override
    public void delete(long id) {
        films.remove(id);
    }

    // вспомогательный метод для генерации идентификатора нового поста
    private int getNextId() {
        int currentMaxId = films.keySet()
                .stream()
                .mapToInt(id -> id)
                .max()
                .orElse(0);
        return ++currentMaxId;
    }

    // вспомогательный метод для проверки валидности фильмов
    private void validateFilms(Film film) {
        if (film.getName() == null || film.getName().isBlank()) {
            log.error("Ошибка: пустое название фильма");
            throw new ValidationException("Название фильма не может быть пустым");
        }
        if (film.getDescription() != null && film.getDescription().length() > 200) {
            log.error("Ошибка: описание превышает лимит 200 символов");
            throw new ValidationException("Описание не может превышать 200 символов");
        }
        if (film.getReleaseDate().isBefore(LocalDate.of(1895, 12, 28))) {
            log.error("Ошибка: некорректная дата релиза");
            throw new ValidationException("Дата релиза не может быть раньше 28 декабря 1895 года");
        }
        if (film.getDuration() <= 0) {
            log.error("Ошибка: продолжительность фильма отрицательная или нулевая");
            throw new ValidationException("Продолжительность фильма не может быть отрицательной или нулевой");
        }
    }
}
