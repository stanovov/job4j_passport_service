package ru.job4j.passportservice.mapper;

import ru.job4j.model.dto.PassportDTO;
import org.springframework.stereotype.Component;
import ru.job4j.passportservice.model.entity.Email;
import ru.job4j.passportservice.model.entity.Passport;

import java.util.Optional;

@Component
public class PassportMapper implements Mapper<Passport, PassportDTO> {

    @Override
    public PassportDTO toDto(Passport passport) {
        return PassportDTO.builder()
                .id(passport.getId())
                .series(passport.getSeries())
                .number(passport.getNumber())
                .name(passport.getName())
                .surname(passport.getSurname())
                .patronymic(passport.getPatronymic())
                .validity(passport.getValidity())
                .email(Optional.ofNullable(passport.getEmail())
                        .map(Email::getEmail).orElse("Не указан"))
                .build();
    }

    @Override
    public Passport toModel(PassportDTO passportDTO) {
        Passport passport = Passport.builder()
                .id(passportDTO.getId())
                .series(passportDTO.getSeries())
                .number(passportDTO.getNumber())
                .name(passportDTO.getName())
                .surname(passportDTO.getSurname())
                .patronymic(Optional.ofNullable(passportDTO.getPatronymic())
                        .orElse(""))
                .validity(passportDTO.getValidity())
                .build();
        passport.setEmail(Optional.ofNullable(passportDTO.getEmail())
                .map(e -> new Email(null, e, passport))
                .orElse(null));
        return passport;
    }
}
