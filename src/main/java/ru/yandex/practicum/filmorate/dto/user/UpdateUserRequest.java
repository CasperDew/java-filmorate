package ru.yandex.practicum.filmorate.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateUserRequest {
    @NotNull
    private Long id;

    @Email(message = "Некорректный Email")
    private String email;
    private String login;
    private String name;

    @PastOrPresent(message = "Дата рождения не может быть будущей датой")
    private LocalDate birthday;

    public boolean hasEmail() {
        return email != null;
    }

    public boolean hasLogin() {
        return login != null;
    }

    public boolean hasName() {
        return name != null;
    }

    public boolean hasBirthday() {
        return birthday != null;
    }
}
