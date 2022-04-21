package ru.job4j.mailservice.service;

import ru.job4j.model.dto.PassportDTO;

import java.util.List;

public interface EmailService {
    void sendExpiredPassportsByEmail(List<PassportDTO> passports);
}
