package ru.job4j.passportservice.service;

import ru.job4j.model.dto.EmailNotificationDTO;

public interface EmailNotificationService {
    void save(EmailNotificationDTO dto);
}
