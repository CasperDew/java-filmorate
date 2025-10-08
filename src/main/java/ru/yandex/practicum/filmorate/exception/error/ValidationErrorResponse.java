package ru.yandex.practicum.filmorate.exception.error;

import java.util.List;

public record ValidationErrorResponse(List<Violation> violations) {
}
