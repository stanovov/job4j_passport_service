package ru.job4j.passportservice.service;

import lombok.RequiredArgsConstructor;
import ru.job4j.model.dto.PassportDTO;
import org.springframework.stereotype.Service;
import ru.job4j.passportservice.handler.PassportNumberAndSeriesAlreadyExist;
import ru.job4j.passportservice.mapper.PassportMapper;
import ru.job4j.passportservice.model.entity.Passport;
import ru.job4j.passportservice.repository.PassportRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PassportServiceImpl implements PassportService {

    private final PassportRepository passportRepository;

    private final PassportMapper passportMapper;

    @Override
    public List<PassportDTO> findAll() {
        List<Passport> passports = new ArrayList<>();
        passportRepository.findAll().forEach(passports::add);
        return passports.stream()
                .map(passportMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PassportDTO> findBySeries(Integer series) {
        return passportRepository.findAllBySeries(series).stream()
                .map(passportMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PassportDTO> findExpired() {
        return passportRepository.findAllWithValidityBefore(LocalDate.now()).stream()
                .map(passportMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PassportDTO> findReplaceable() {
        LocalDate now = LocalDate.now();
        LocalDate in3Mouths = now.plusMonths(3);
        return passportRepository.findAllByValidityBetween(now, in3Mouths).stream()
                .map(passportMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PassportDTO> findById(Integer id) {
        return passportRepository.findById(id).map(passportMapper::toDto);
    }

    @Override
    public PassportDTO saveOrUpdate(PassportDTO dto) {
        if (dto.getId() == null
                && passportRepository.existsByNumberAndSeries(dto.getNumber(), dto.getSeries())) {
            throw new PassportNumberAndSeriesAlreadyExist();
        }
        Passport passportEntity = passportMapper.toModel(dto);
        return passportMapper.toDto(
                passportRepository.save(passportEntity)
        );
    }

    @Override
    public void deleteById(Integer id) {
        passportRepository.deleteById(id);
    }
}
