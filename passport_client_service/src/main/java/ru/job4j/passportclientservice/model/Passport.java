package ru.job4j.passportclientservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode(of = { "id" })
@NoArgsConstructor
@AllArgsConstructor
public class Passport {

    private Integer id;

    private Integer number;

    private Integer series;

    private String name;

    private String surname;

    private String patronymic;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate validity;
}
