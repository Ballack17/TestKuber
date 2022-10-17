package kubertest.master.service;

import kubertest.master.BaseTestClassWithPostgreSQL;
import kubertest.master.config.ConfirmationMailConfig;
import kubertest.master.data.dto.FullEssenceDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Order(4)
@RequiredArgsConstructor
class MailServiceTest extends BaseTestClassWithPostgreSQL {


    @Autowired
    ConfirmationMailConfig confirmationMailConfig;

    @Autowired
    MailService mailService;

    @SneakyThrows
    @Test
    void sendTest() {
        FullEssenceDto fullEssenceDto = new FullEssenceDto();
        fullEssenceDto.setId(UUID.fromString("00000001-0000-0000-0000-000000000003"));
        fullEssenceDto.setSomeDate(LocalDate.now());
        fullEssenceDto.setSomeTime(LocalTime.now());
        fullEssenceDto.setTitle("oops");
        fullEssenceDto.setEmail("d.davydenka@yandex.ru");
        mailService.send(fullEssenceDto, confirmationMailConfig);
    }
}