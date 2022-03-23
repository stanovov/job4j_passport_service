package ru.job4j.passportclientservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.job4j.passportclientservice.model.Passport;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PassportServiceImpl implements PassportService {

    @Value("${rest-api.url}")
    private String url;

    private final RestTemplate restTemplate;

    @Override
    public List<Passport> findAll() {
        return findPassports(
                String.format("%s/find", url));
    }

    @Override
    public List<Passport> findBySeries(Integer series) {
        return findPassports(
                String.format("%s/find-by-series?series=%d", url, series));
    }

    @Override
    public List<Passport> findExpired() {
        return findPassports(
                String.format("%s/find-expired", url));
    }

    @Override
    public List<Passport> findReplaceable() {
        return findPassports(
                String.format("%s/find-replaceable", url));
    }

    @Override
    public Passport findById(Integer id) {
        return restTemplate.getForObject(
                String.format("%s/find/{id}", url), Passport.class, id);
    }

    @Override
    public Passport save(Passport passport) {
        return restTemplate.postForObject(url, passport, Passport.class);
    }

    @Override
    public void update(Passport passport) {
        restTemplate.put(url, passport);
    }

    @Override
    public void deleteById(Integer id) {
        restTemplate.delete(
                String.format("%s/{id}", url), id);
    }

    private List<Passport> findPassports(String specificUrl) {
        return restTemplate.exchange(
                specificUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Passport>>() { }
        ).getBody();
    }
}
