package ru.job4j.passportclientservice.service;

import ru.job4j.passportclientservice.model.Passport;

import java.util.List;

public interface PassportService {

    List<Passport> findAll();

    List<Passport> findBySeries(Integer series);

    List<Passport> findExpired();

    List<Passport> findReplaceable();

    Passport findById(Integer id);

    Passport save(Passport passport);

    void update(Passport passport);

    void deleteById(Integer id);

}
