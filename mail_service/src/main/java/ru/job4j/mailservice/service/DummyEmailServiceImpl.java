package ru.job4j.mailservice.service;

import model.dto.PassportDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DummyEmailServiceImpl implements EmailService {
    @Override
    public void sendExpiredPassportsByEmail(List<PassportDTO> passports) {
        passports.forEach(System.out::println);
    }
}
