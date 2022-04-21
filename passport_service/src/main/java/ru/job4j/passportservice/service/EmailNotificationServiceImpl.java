package ru.job4j.passportservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.model.dto.EmailNotificationDTO;
import ru.job4j.passportservice.model.entity.Email;
import ru.job4j.passportservice.model.entity.EmailNotification;
import ru.job4j.passportservice.repository.EmailNotificationRepository;
import ru.job4j.passportservice.repository.EmailRepository;

import java.sql.SQLException;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailNotificationServiceImpl implements EmailNotificationService {

    private final EmailRepository emailRepository;

    private final EmailNotificationRepository emailNotificationRepository;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = { IllegalArgumentException.class, SQLException.class })
    public void save(EmailNotificationDTO dto) {
        Email email = emailRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Unable to find email '%s'", dto.getEmail())));
        if (emailNotificationRepository.existsByEmail(email)) {
            emailNotificationRepository.deleteByEmail(email);
        }
        EmailNotification emailNotification = emailNotificationRepository.save(
                new EmailNotification(null, dto.getLastUpdate(), email));
        log.info(String.format("Email Notification Saved: %s", emailNotification));
    }
}
