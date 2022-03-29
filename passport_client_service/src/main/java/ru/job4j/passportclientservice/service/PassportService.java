package ru.job4j.passportclientservice.service;

import model.dto.PassportDTO;

import java.util.List;

public interface PassportService {

    List<PassportDTO> findAll();

    List<PassportDTO> findBySeries(Integer series);

    List<PassportDTO> findExpired();

    List<PassportDTO> findReplaceable();

    PassportDTO findById(Integer id);

    PassportDTO save(PassportDTO dto);

    void update(PassportDTO dto);

    void deleteById(Integer id);

}
