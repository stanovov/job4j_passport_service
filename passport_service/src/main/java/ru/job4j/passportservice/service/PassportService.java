package ru.job4j.passportservice.service;

import model.dto.PassportDTO;

import java.util.List;
import java.util.Optional;

public interface PassportService {

    List<PassportDTO> findAll();

    List<PassportDTO> findBySeries(Integer series);

    List<PassportDTO> findExpired();

    List<PassportDTO> findReplaceable();

    Optional<PassportDTO> findById(Integer id);

    PassportDTO saveOrUpdate(PassportDTO dto);

    void deleteById(Integer id);

}
