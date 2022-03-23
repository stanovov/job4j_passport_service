package ru.job4j.passportservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import ru.job4j.passportservice.handler.Operation;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode(of = { "id" })
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "passports",
        uniqueConstraints = @UniqueConstraint(
                name = "NUMBER_SERIES",
                columnNames = { "number", "series" }
        )
)
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Null(message = "When creating Id must be empty", groups = Operation.OnCreate.class)
    @NotNull(message = "When updating Id must be not empty", groups = Operation.OnUpdate.class)
    private Integer id;

    @Column(name = "number", nullable = false)
    @Min(value = 100000, message = "Number should not be less than 100000")
    @Max(value = 999999, message = "Number should not be greater than 999999")
    @NotNull(message = "Number must be not empty")
    private Integer number;

    @Column(name = "series", nullable = false)
    @Min(value = 1000, message = "Series should not be less than 1000")
    @Max(value = 9999, message = "Series should not be greater than 9999")
    @NotNull(message = "Series must be not empty")
    private Integer series;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Name must be not empty")
    private String name;

    @Column(name = "surname", nullable = false)
    @NotBlank(message = "Surname must be not empty")
    private String surname;

    @Column(name = "patronymic", nullable = false)
    private String patronymic = "";

    @Column(name = "validity", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    @NotNull(message = "Validity must be not empty")
    private LocalDate validity;
}
