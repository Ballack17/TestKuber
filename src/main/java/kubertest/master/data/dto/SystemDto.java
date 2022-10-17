package kubertest.master.data.dto;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.LocalDate;


@Value
@AllArgsConstructor
public class SystemDto {


    private String javaVersion;

    private LocalDate localDate;
}
