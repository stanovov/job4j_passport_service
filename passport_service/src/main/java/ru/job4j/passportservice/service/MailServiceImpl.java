package ru.job4j.passportservice.service;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.job4j.passportservice.model.Passport;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final Gson gson;

    private final KafkaTemplate<Integer, String> kafkaTemplate;

    private final PassportService passportService;

    @Override
    @Scheduled(fixedDelay = 10_000)
    public void notification() {
        List<Passport> expiredPassports = passportService.findExpired();
        if (expiredPassports.size() == 0) {
            return;
        }
        log.info("Number of expired passports: " + expiredPassports.size());
        kafkaTemplate.send("expiredPassports", gson.toJson(expiredPassports));
        String a = "";
    }
}
