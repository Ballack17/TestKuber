package kubertest.master.data.dto;


import lombok.*;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullEssenceDto {

    @NotBlank
    private UUID id;

    @NotBlank
    private LocalDate someDate;

    @NotBlank
    private LocalTime someTime;

    @NotBlank
    @Length(max = 255)
    private String title;

    @Email
    @NotBlank
    private String email;

}

