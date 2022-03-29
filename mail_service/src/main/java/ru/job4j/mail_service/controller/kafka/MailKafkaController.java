package ru.job4j.mail_service.controller.kafka;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.job4j.mail_service.gson.typetoken.PassportTypeToken;
import ru.job4j.mail_service.model.Passport;
import ru.job4j.mail_service.service.EmailService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MailKafkaController {

    private final Gson gson;

    private final EmailService emailService;

    @KafkaListener(topics = {"expiredPassports"})
    public void listenExpiredPassports(ConsumerRecord<Integer, String> input) {
        List<Passport> passports = gson.fromJson(input.value(), new PassportTypeToken().getType());
        emailService.sendExpiredPassportsByEmail(passports);
    }

}
