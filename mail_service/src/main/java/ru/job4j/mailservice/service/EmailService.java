package ru.job4j.mailservice.service;

import model.dto.PassportDTO;

import java.util.List;

public interface EmailService {
    void sendExpiredPassportsByEmail(List<PassportDTO> passports);
}
