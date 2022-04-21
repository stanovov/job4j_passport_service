package ru.job4j.passportservice.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.passportservice.model.entity.Email;
import ru.job4j.passportservice.model.entity.EmailNotification;

public interface EmailNotificationRepository extends CrudRepository<EmailNotification, Integer> {
    boolean existsByEmail(Email email);
    void deleteByEmail(Email email);
}
