package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.dto.mparating.MPARatingDto;
import ru.yandex.practicum.filmorate.service.mparating.MPARatingService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/mpa")
@RequiredArgsConstructor
public class MPARatingController {
    private final MPARatingService mpaService;

    @GetMapping
    public List<MPARatingDto> findAll() {
        log.trace("Получен список всего доступного рейтинга");
        return mpaService.findAll();
    }

    @GetMapping("/{mpaId}")
    public MPARatingDto findById(@PathVariable @Positive long mpaId) {
        log.trace("Получен MPA Rating с id {}", mpaId);
        return mpaService.findById(mpaId);
    }
}
