package ru.yandex.practicum.filmorate.service.film;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.dto.film.FilmDto;
import ru.yandex.practicum.filmorate.dto.film.NewFilmRequest;
import ru.yandex.practicum.filmorate.dto.film.UpdateFilmRequest;
import ru.yandex.practicum.filmorate.dto.genre.GenreDto;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.mapper.FilmMapper;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.repository.film.FilmRepository;
import ru.yandex.practicum.filmorate.repository.genre.GenreRepository;
import ru.yandex.practicum.filmorate.repository.like.LikesRepository;
import ru.yandex.practicum.filmorate.repository.mparating.MPARatingRepository;
import ru.yandex.practicum.filmorate.repository.user.UserRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class FilmsServiceImpl implements FilmsService {

    private FilmRepository filmRepository;
    private UserRepository userRepository;
    private LikesRepository likesRepository;
    private GenreRepository genreRepository;
    private MPARatingRepository mpaRepository;

    @Override
    public List<FilmDto> findAll() {
        return filmRepository.findAll()
                .stream()
                .map(FilmMapper::toFilmDto)
                .toList();
    }

    @Override
    public FilmDto findById(long id) {
        return filmRepository.findById(id)
                .map(FilmMapper::toFilmDto)
                .orElseThrow(NotFoundException.supplier("Фильм с id " + id + " не найден"));
    }

    @Override
    public FilmDto create(NewFilmRequest request) {
        Film film = FilmMapper.toFilm(request);

        throwIfMpaRatingNotFound(request.getMpa().id());
        List<Long> genreIds = request.getGenres().stream()
                .distinct()
                .map(GenreDto::id)
                .toList();

        List<Genre> genres = genreRepository.getByIds(genreIds);


        if (genreIds.size() != genres.size()) {
            throw new NotFoundException("Жанр не найден");
        }

        film = filmRepository.save(film);
        log.info("Добавлен фильм: {} {}", film.getId(), film);
        return FilmMapper.toFilmDto(film);
    }

    @Override
    public FilmDto update(UpdateFilmRequest request) {
        Film film = filmRepository.findById(request.getId()).orElseThrow(
                NotFoundException.supplier("Фильм с id " + request.getId() + "Не найден")
        );

        throwIfMpaRatingNotFound(request.getMpa().id());

        if (request.hasGenres()) {
            List<Long> genreIds = request.getGenres().stream()
                    .distinct()
                    .map(GenreDto::id)
                    .toList();

            List<Genre> genres = genreRepository.getByIds(genreIds);

            if (genreIds.size() != genres.size()) {
                throw new NotFoundException("Жанр не найден");
            }
        }

        film = FilmMapper.updateFilmFields(film, request);
        log.info("Фильм с id {} был обновлен", film.getId());
        filmRepository.update(film);
        return FilmMapper.toFilmDto(film);
    }

    @Override
    public void deleteById(Long id) {
        filmRepository.deleteById(id);
        log.info("Фильм с id {} был удален", id);
    }

    @Override
    public void addLike(Long filmId, Long userId) {
        throwIfFilmNotFound(filmId);
        throwIfUserNotFound(userId);
        likesRepository.addLike(userId, filmId);
        log.info("Пользователь {} поставил лайк фильму {}", userId, filmId);
    }

    @Override
    public void removeLike(Long filmId, Long userId) {
        throwIfFilmNotFound(filmId);
        throwIfUserNotFound(userId);
        likesRepository.removeLike(userId, filmId);
        log.info("Пользователь {} удалил лайк фильму {}", userId, filmId);
    }

    @Override
    public List<FilmDto> findFilmsWithTopLikes(int count) {
        return filmRepository.findTopPopularFilms(count)
                .stream()
                .map(FilmMapper::toFilmDto)
                .toList();
    }

    private void throwIfUserNotFound(Long userId) {
        userRepository.getById(userId)
                .orElseThrow(NotFoundException.supplier("Пользователь с id " + userId + " не найден"));
    }

    private void throwIfFilmNotFound(Long filmId) {
        filmRepository.findById(filmId)
                .orElseThrow(NotFoundException.supplier("Фильм с id " + filmId + " не найден", filmId));
    }

    private void throwIfMpaRatingNotFound(Long mpaRatingId) {
        mpaRepository.findById(mpaRatingId)
                .orElseThrow(NotFoundException.supplier("MPA рейтинг id " + mpaRatingId + " не найден"));
    }
}
