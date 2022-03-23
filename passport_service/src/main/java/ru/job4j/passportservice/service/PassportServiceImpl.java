package ru.job4j.passportservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.passportservice.handler.PassportNumberAndSeriesAlreadyExist;
import ru.job4j.passportservice.model.Passport;
import ru.job4j.passportservice.repository.PassportRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PassportServiceImpl implements PassportService {

    private final PassportRepository passportRepository;

    @Override
    public List<Passport> findAll() {
        List<Passport> passports = new ArrayList<>();
        passportRepository.findAll().forEach(passports::add);
        return passports;
    }

    @Override
    public List<Passport> findBySeries(Integer series) {
        return passportRepository.findAllBySeries(series);
    }

    @Override
    public List<Passport> findExpired() {
        return passportRepository.findAllWithValidityBefore(
                LocalDate.now()
        );
    }

    @Override
    public List<Passport> findReplaceable() {
        LocalDate now = LocalDate.now();
        LocalDate in3Mouths = now.plusMonths(3);
        return passportRepository.findAllByValidityBetween(now, in3Mouths);
    }

    @Override
    public Optional<Passport> findById(Integer id) {
        return passportRepository.findById(id);
    }

    @Override
    public Passport saveOrUpdate(Passport passport) {
        if (passport.getId() == null
                && passportRepository.existsByNumberAndSeries(passport.getNumber(), passport.getSeries())) {
            throw new PassportNumberAndSeriesAlreadyExist();
        }
        return passportRepository.save(passport);
    }

    @Override
    public void deleteById(Integer id) {
        passportRepository.deleteById(id);
    }
}
