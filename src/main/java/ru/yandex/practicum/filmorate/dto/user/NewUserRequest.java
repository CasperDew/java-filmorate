package ru.yandex.practicum.filmorate.dto.user;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class NewUserRequest {
    @NotBlank(message = "Email не может быть пустым")
    @Email(message = "Некорректный Email")
    private String email;

    @NotBlank(message = "Логин не может быть пустым")
    @Pattern(regexp = "^\\S+$", message = "Логин не может содержать пробелы")
    private String login;
    private String name;

    @NotNull(message = "День рождения не может быть пустым")
    @Past(message = "Дата рождения не может быть будущей датой")
    private LocalDate birthday;

}
