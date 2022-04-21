package ru.job4j.passportservice.controller.kafka;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.job4j.model.dto.EmailNotificationDTO;
import ru.job4j.passportservice.gson.typetoken.EmailNotificationTypeToken;
import ru.job4j.passportservice.service.EmailNotificationService;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailNotificationKafkaController {

    private final Gson gson;

    private final EmailNotificationService emailNotificationService;

    @KafkaListener(topics = {"successfulEmailSending"})
    public void listenerSuccessfulEmailSending(ConsumerRecord<Integer, String> input) {
        log.info("Topic \"successfulEmailSending\" got message");
        List<EmailNotificationDTO> notificationDTOs = gson.fromJson(input.value(), new EmailNotificationTypeToken().getType());
        notificationDTOs.forEach(emailNotificationService::save);
    }

}
