package ru.job4j.mailservice.controller.kafka;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import model.dto.PassportDTO;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.job4j.mailservice.gson.typetoken.PassportTypeToken;
import ru.job4j.mailservice.service.EmailService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MailKafkaController {

    private final Gson gson;

    private final EmailService emailService;

    @KafkaListener(topics = {"expiredPassports"})
    public void listenExpiredPassports(ConsumerRecord<Integer, String> input) {
        List<PassportDTO> passports = gson.fromJson(input.value(), new PassportTypeToken().getType());
        emailService.sendExpiredPassportsByEmail(passports);
    }

}
