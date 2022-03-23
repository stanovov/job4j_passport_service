package ru.job4j.passportservice.service;

import ru.job4j.passportservice.model.Passport;

import java.util.List;
import java.util.Optional;

public interface PassportService {

    List<Passport> findAll();

    List<Passport> findBySeries(Integer series);

    List<Passport> findExpired();

    List<Passport> findReplaceable();

    Optional<Passport> findById(Integer id);

    Passport saveOrUpdate(Passport passport);

    void deleteById(Integer id);

}
