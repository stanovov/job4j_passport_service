package ru.job4j.mail_service.service;

import ru.job4j.mail_service.model.Passport;

import java.util.List;

public interface EmailService {
    void sendExpiredPassportsByEmail(List<Passport> passports);
}
