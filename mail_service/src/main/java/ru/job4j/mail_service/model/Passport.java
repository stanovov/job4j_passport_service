package ru.job4j.mail_service.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode(of = { "id" })
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Passport {

    private Integer id;

    private Integer number;

    private Integer series;

    private String name;

    private String surname;

    private String patronymic;

    private LocalDate validity;
}
