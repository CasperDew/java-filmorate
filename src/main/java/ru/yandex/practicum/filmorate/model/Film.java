package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Film.
 */
@Data
@Builder
public class Film {
    private Long id;
    private String name;
    private String description;
    private LocalDate releaseDate;
    private Integer duration;
    private MPARating mpaRating;
    @Builder.Default
    private Set<Genre> genres = new HashSet<>();
    @Builder.Default
    private Set<Long> usersWhoLiked = new HashSet<>();

    public void addGenre(Genre genre) {
        this.genres.add(genre);
    }
}
