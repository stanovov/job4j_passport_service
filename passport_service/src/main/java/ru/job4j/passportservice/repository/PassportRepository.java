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

    @Query(value = "SELECT p.id, p.number, p.series, p.name, p.surname, p.patronymic, p.validity, p.email_id "
                    + "FROM passports AS p "
                        + "INNER JOIN emails e on p.email_id = e.id "
                        + "LEFT JOIN email_notifications en on e.id = en.email_id "
                    + "WHERE p.validity <= now() AND (en.id IS NULL OR en.last_notify <= now() - interval '1 DAY')",
            nativeQuery = true)
    List<Passport> findExpired();

    boolean existsByNumberAndSeries(Integer number, Integer series);
}
