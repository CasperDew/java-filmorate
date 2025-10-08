package ru.yandex.practicum.filmorate.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder(toBuilder = true)
public class UserDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String email;
    private String login;
    private String name;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate birthday;

    private Set<Long> friends;
}
