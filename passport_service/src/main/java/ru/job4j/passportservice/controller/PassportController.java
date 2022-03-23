package ru.job4j.passportservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.passportservice.handler.Operation;
import ru.job4j.passportservice.model.Passport;
import ru.job4j.passportservice.service.PassportService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/passports")
@RequiredArgsConstructor
public class PassportController {

    private final PassportService passportService;

    @GetMapping("/find")
    public ResponseEntity<List<Passport>> findAll() {
        return new ResponseEntity<>(
                passportService.findAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/find-by-series")
    public ResponseEntity<List<Passport>> findBySeries(@RequestParam Integer series) {
        return new ResponseEntity<>(
                passportService.findBySeries(series),
                HttpStatus.OK
        );
    }

    @GetMapping("/find-expired")
    public ResponseEntity<List<Passport>> findExpired() {
        return new ResponseEntity<>(
                passportService.findExpired(),
                HttpStatus.OK
        );
    }

    @GetMapping("/find-replaceable")
    public ResponseEntity<List<Passport>> findReplaceable() {
        return new ResponseEntity<>(
                passportService.findReplaceable(),
                HttpStatus.OK
        );
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Passport> findById(@PathVariable Integer id) {
        return passportService.findById(id)
                .map(passport -> ResponseEntity.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(passport))
                .orElseThrow(() -> {
                    String msg = String.format("Passport is not found by id %s", id);
                    log.info(msg);
                    return new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            msg
                    );
                });
    }

    @PostMapping("/save")
    @Validated(Operation.OnCreate.class)
    public ResponseEntity<Passport> create(@Valid @RequestBody Passport passport) {
        return new ResponseEntity<>(
                passportService.saveOrUpdate(passport),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/update")
    @Validated(Operation.OnUpdate.class)
    public ResponseEntity<Void> update(@Valid @RequestBody Passport passport) {
        passportService.saveOrUpdate(passport);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        passportService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
