package ru.job4j.passportclientservice.service;

import lombok.RequiredArgsConstructor;
import ru.job4j.model.dto.PassportDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PassportServiceImpl implements PassportService {

    @Value("${rest-api.url}")
    private String url;

    private final RestTemplate restTemplate;

    @Override
    public List<PassportDTO> findAll() {
        return findPassports(
                String.format("%s/find", url));
    }

    @Override
    public List<PassportDTO> findBySeries(Integer series) {
        return findPassports(
                String.format("%s/find-by-series?series=%d", url, series));
    }

    @Override
    public List<PassportDTO> findExpired() {
        return findPassports(
                String.format("%s/find-expired", url));
    }

    @Override
    public List<PassportDTO> findReplaceable() {
        return findPassports(
                String.format("%s/find-replaceable", url));
    }

    @Override
    public PassportDTO findById(Integer id) {
        return restTemplate.getForObject(
                String.format("%s/find/{id}", url), PassportDTO.class, id);
    }

    @Override
    public PassportDTO save(PassportDTO dto) {
        return restTemplate.postForObject(url, dto, PassportDTO.class);
    }

    @Override
    public void update(PassportDTO dto) {
        restTemplate.put(url, dto);
    }

    @Override
    public void deleteById(Integer id) {
        restTemplate.delete(
                String.format("%s/{id}", url), id);
    }

    private List<PassportDTO> findPassports(String specificUrl) {
        return restTemplate.exchange(
                specificUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<PassportDTO>>() { }
        ).getBody();
    }
}
