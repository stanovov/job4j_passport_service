package ru.job4j.passportservice.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.passportservice.model.entity.Email;

import java.util.Optional;

public interface EmailRepository extends CrudRepository<Email, Integer> {
    Optional<Email> findByEmail(String email);
}
