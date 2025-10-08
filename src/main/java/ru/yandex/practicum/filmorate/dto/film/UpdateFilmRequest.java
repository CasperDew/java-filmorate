package ru.yandex.practicum.filmorate.dto.film;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ru.yandex.practicum.filmorate.dto.genre.GenreDto;
import ru.yandex.practicum.filmorate.dto.mparating.MPARatingDto;

import java.time.LocalDate;
import java.util.List;

@Data
public class UpdateFilmRequest {
    @NotNull
    private Long id;
    private String name;

    @Size(message = "Длина описания фильма не может быть длиннее 200 символов", max = 200)
    private String description;
    private LocalDate releaseDate;

    @Positive(message = "Продолжительность фильма должна быть положительной")
    private Integer duration;
    private MPARatingDto mpa;
    private List<GenreDto> genres;

    public boolean hasName() {
        return name != null && !name.isEmpty();
    }

    public boolean hasDescription() {
        return description != null && !description.isEmpty();
    }

    public boolean hasReleaseDate() {
        return releaseDate != null;
    }

    public boolean hasDuration() {
        return duration != null;
    }

    public boolean hasGenres() {
        return genres != null && !genres.isEmpty();
    }

    public boolean hasMpa() {
        return mpa != null && mpa.id() != null;
    }
}
