package ru.job4j.passportservice.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.passportservice.model.entity.Passport;

import java.time.LocalDate;
import java.util.List;

public interface PassportRepository extends CrudRepository<Passport, Integer> {

    List<Passport> findAllBySeries(Integer series);

    List<Passport> findAllByValidityBetween(LocalDate start, LocalDate end);

    @Query("SELECT p FROM Passport p WHERE p.validity <= :date")
    List<Passport> findAllWithValidityBefore(LocalDate date);

    boolean existsByNumberAndSeries(Integer number, Integer series);
}
