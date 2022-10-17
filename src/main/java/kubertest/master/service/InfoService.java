package kubertest.master.service;

import kubertest.master.data.dto.SystemDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class InfoService {

    public SystemDto getInfo() {
        Runtime.Version version = java.lang.Runtime.version();

        return new SystemDto(version.toString(), LocalDate.now());

    }


}
