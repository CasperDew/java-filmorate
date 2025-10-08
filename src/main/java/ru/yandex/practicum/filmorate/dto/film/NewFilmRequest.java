package ru.yandex.practicum.filmorate.dto.film;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ru.yandex.practicum.filmorate.dto.genre.GenreDto;
import ru.yandex.practicum.filmorate.dto.mparating.MPARatingDto;
import ru.yandex.practicum.filmorate.validation.AfterData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class NewFilmRequest {
    @NotBlank(message = "Название фильма не может быть пустым")
    private String name;

    @Size(message = "Длина описания фильма не может быть длиннее 200 символов", max = 200)
    @NotBlank(message = "Описание фильма не может быть пустым")
    private String description;

    @NotNull(message = "Дата выпуска не может быть пустой")
    @AfterData(value = "1895-12-28", message = "Дата выхода фильма не может быть раньше 28 декабря 1895 года")
    private LocalDate releaseDate;

    @NotNull(message = "Продолжительность не может быть пустой")
    @Positive(message = "Продолжительность фильма должна быть положительной")
    private Integer duration;

    private MPARatingDto mpa;

    @JsonSetter(nulls = Nulls.AS_EMPTY)
    private List<GenreDto> genres = new ArrayList<>();
}
