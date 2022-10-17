package kubertest.master.data.dto;


import lombok.AllArgsConstructor;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalTime;

@Value
@AllArgsConstructor
public class EssenceEmailOffDto {

    @NotBlank
    LocalDate someDate;

    @NotBlank
    LocalTime someTime;

    @NotBlank
    @Length(max = 255)
    String title;


}

