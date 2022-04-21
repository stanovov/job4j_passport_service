package ru.job4j.passportservice.service;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.job4j.model.dto.PassportDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.job4j.passportservice.mapper.PassportMapper;
import ru.job4j.passportservice.repository.PassportRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final Gson gson;

    private final KafkaTemplate<Integer, String> kafkaTemplate;

    private final PassportRepository passportRepository;

    private final PassportMapper passportMapper;

    @Override
    @Scheduled(fixedDelay = 30_000)
    public void notification() {
        final String topic = "expiredPassports";
        List<PassportDTO> expiredPassports = passportRepository
                .findExpired()
                .stream()
                .map(passportMapper::toDto)
                .collect(Collectors.toList());
        if (expiredPassports.size() == 0) {
            return;
        }
        log.info(String.format(
                "%d expired passports sent to mailing service. Topic = '%s'",
                expiredPassports.size(),
                topic));
        kafkaTemplate.send(topic, gson.toJson(expiredPassports));
    }
}
