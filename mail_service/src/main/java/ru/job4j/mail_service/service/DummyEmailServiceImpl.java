package ru.job4j.mail_service.service;

import org.springframework.stereotype.Service;
import ru.job4j.mail_service.model.Passport;

import java.util.List;

@Service
public class DummyEmailServiceImpl implements EmailService {
    @Override
    public void sendExpiredPassportsByEmail(List<Passport> passports) {
        passports.forEach(System.out::println);
    }
}
