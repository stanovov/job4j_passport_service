package ru.job4j.passportclientservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.passportclientservice.model.Passport;
import ru.job4j.passportclientservice.service.PassportService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/passports")
@RequiredArgsConstructor
public class PassportAPIController {

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
        return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(passportService.findById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<Passport> create(@RequestBody Passport passport) {
        return new ResponseEntity<>(
                passportService.save(passport),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/update")
    public ResponseEntity<Void> update(@RequestBody Passport passport) {
        passportService.update(passport);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        passportService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
