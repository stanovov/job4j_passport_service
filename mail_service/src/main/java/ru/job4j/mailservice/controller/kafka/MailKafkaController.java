package ru.job4j.mailservice.controller.kafka;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import ru.job4j.model.dto.PassportDTO;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.job4j.mailservice.gson.typetoken.PassportTypeToken;
import ru.job4j.mailservice.service.EmailService;

import java.util.List;

@Slf4j
@Component
public class MailKafkaController {

    private final Gson gson;

    private final EmailService emailService;

    @Autowired
    public MailKafkaController(Gson gson, @Qualifier("emailServiceImpl") EmailService emailService) {
        this.gson = gson;
        this.emailService = emailService;
    }

    @KafkaListener(topics = {"expiredPassports"})
    public void listenExpiredPassports(ConsumerRecord<Integer, String> input) {
        log.info("Topic \"expiredPassports\" got message");
        List<PassportDTO> passports = gson.fromJson(input.value(), new PassportTypeToken().getType());
        emailService.sendExpiredPassportsByEmail(passports);
    }

}
