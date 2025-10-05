package ru.yandex.practicum.filmorate.mapper;

import lombok.experimental.UtilityClass;
import ru.yandex.practicum.filmorate.dto.mparating.MPARatingDto;
import ru.yandex.practicum.filmorate.model.MPARating;

@UtilityClass
public class MPARatingMapper {
    public MPARatingDto toMPARatingDto(MPARating mpaRating) {
        return new MPARatingDto(mpaRating.getId(), mpaRating.getName());
    }

    public MPARating toMPARating(MPARatingDto mpaRatingDto) {
        return MPARating.builder()
                .id(mpaRatingDto.id())
                .name(mpaRatingDto.name())
                .build();
    }
}
