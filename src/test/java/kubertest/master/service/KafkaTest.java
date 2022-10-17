package kubertest.master.service;

import kubertest.master.BaseTestClassWithPostgreSQL;
import kubertest.master.config.ConfirmationMailConfig;
import kubertest.master.data.dto.FullEssenceDto;
import kubertest.master.integration.kafka.AddingEssenceListener;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class KafkaTest extends BaseTestClassWithPostgreSQL {

    @Autowired
    AddingEssenceListener addingEssenceListener;

    @MockBean
    MailService mailService;

    @Autowired
    EssenceService essenceService;

    @MockBean
    ConfirmationMailConfig confirmationMailConfig;

    @Test
    public void retrieveMessageTestFalse() {
        FullEssenceDto essenceDto = new FullEssenceDto();
        essenceDto.setId(UUID.fromString("8a9fe7b6-bb38-11ec-8422-0242ac120002"));
        essenceDto.setSomeDate(LocalDate.now());
        essenceDto.setSomeTime(LocalTime.now());
        essenceDto.setEmail("example11@gmail.com");
        assertFalse(addingEssenceListener.retrieveMessage(essenceDto));
    }

    @Test
    @Sql(scripts = "/insert", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void retrieveMessageTestFalseIllegalArgument() {
        FullEssenceDto essenceDto = new FullEssenceDto();
        essenceDto.setId(UUID.fromString("00000001-0000-0000-0000-000000000001"));
        essenceDto.setSomeDate(LocalDate.now());
        essenceDto.setSomeTime(LocalTime.now());
        essenceDto.setTitle("oops");
        essenceDto.setEmail("example11@gmail.com");
        assertFalse(addingEssenceListener.retrieveMessage(essenceDto));
    }

    @Test
        public void retrieveMessageTest() {
        FullEssenceDto essenceDto = new FullEssenceDto();
        essenceDto.setId(UUID.fromString("8a9fe7b6-bb38-11ec-8422-0242ac120002"));
        essenceDto.setSomeDate(LocalDate.now());
        essenceDto.setSomeTime(LocalTime.now());
        essenceDto.setTitle("oops");
        essenceDto.setEmail("example11@gmail.com");
        assertTrue(addingEssenceListener.retrieveMessage(essenceDto));
    }


}
