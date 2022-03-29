package ru.job4j.passportservice.mapper;

import model.dto.PassportDTO;
import org.springframework.stereotype.Component;
import ru.job4j.passportservice.model.entity.Passport;

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
                .build();
    }

    @Override
    public Passport toModel(PassportDTO passportDTO) {
        return Passport.builder()
                .id(passportDTO.getId())
                .series(passportDTO.getSeries())
                .number(passportDTO.getNumber())
                .name(passportDTO.getName())
                .surname(passportDTO.getSurname())
                .patronymic(passportDTO.getPatronymic())
                .validity(passportDTO.getValidity())
                .build();
    }
}
