package ru.job4j.mailservice.service;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import ru.job4j.model.dto.EmailNotificationDTO;
import ru.job4j.model.dto.PassportDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@PropertySource("classpath:mail.properties")
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Value("${mail.user}")
    private String from;

    private final Gson gson;

    private final KafkaTemplate<Integer, String> kafkaTemplate;

    @Override
    public void sendExpiredPassportsByEmail(List<PassportDTO> passports) {
        List<EmailNotificationDTO> sentEmails = new ArrayList<>();
        passports.forEach(passport -> {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(from);
            mailMessage.setTo(passport.getEmail());
            mailMessage.setSubject("Паспорт просрочен");
            mailMessage.setText(getEmailText(passport));
            try {
                mailSender.send(mailMessage);
                log.info(String.format(
                        "Expired passport notification sent to '%s' email address",
                        passport.getEmail()));
                sentEmails.add(new EmailNotificationDTO(
                        passport.getEmail(),
                        passport.getValidity()
                ));
            } catch (MailException e) {
                log.warn(String.format(
                        "Failed to send email to '%s'",
                        passport.getEmail()));
            }
            notification(sentEmails);
        });
    }

    private void notification(List<EmailNotificationDTO> sentEmails) {
        final String topic = "successfulEmailSending";
        log.info(String.format(
                "Number of successfully sent emails %d. Topic = '%s'",
                sentEmails.size(),
                topic));
        kafkaTemplate.send(topic, gson.toJson(sentEmails));
    }

    private String getEmailText(PassportDTO passport) {
        return "Паспорт\n"
                + "Серия: " + passport.getSeries() + ", "
                + "Номер: " + passport.getNumber() + "\n"
                + "ФИО: "
                + passport.getSurname() + " "
                + passport.getName() + " "
                + passport.getPatronymic() + "\n"
                + "Был просрочен - "
                + passport.getValidity().format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
