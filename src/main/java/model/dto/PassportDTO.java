package model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import validation.Operation;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Builder
public class PassportDTO {

    @Null(message = "When creating Id must be empty", groups = Operation.OnCreate.class)
    @NotNull(message = "When updating Id must be not empty", groups = Operation.OnUpdate.class)
    private Integer id;

    @Min(value = 100000, message = "Number should not be less than 100000")
    @Max(value = 999999, message = "Number should not be greater than 999999")
    @NotNull(message = "Number must be not empty")
    private Integer number;

    @Min(value = 1000, message = "Series should not be less than 1000")
    @Max(value = 9999, message = "Series should not be greater than 9999")
    @NotNull(message = "Series must be not empty")
    private Integer series;

    @NotBlank(message = "Name must be not empty")
    private String name;

    @NotBlank(message = "Surname must be not empty")
    private String surname;

    private String patronymic;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @NotNull(message = "Validity must be not empty")
    private LocalDate validity;
}
