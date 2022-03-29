package ru.job4j.passportservice.mapper;

public interface Mapper<T, K> {

    K toDto(T t);

    T toModel(K k);
}
