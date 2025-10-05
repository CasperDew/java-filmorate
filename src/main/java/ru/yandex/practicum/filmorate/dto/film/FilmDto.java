package ru.yandex.practicum.filmorate.dto.film;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import ru.yandex.practicum.filmorate.dto.genre.GenreDto;
import ru.yandex.practicum.filmorate.dto.mparating.MPARatingDto;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class FilmDto {
    private Long id;
    private String name;
    private String description;
    private LocalDate releaseDate;
    private Integer duration;
    private MPARatingDto mpaRating;

    @Singular
    private List<GenreDto> genres;

}
