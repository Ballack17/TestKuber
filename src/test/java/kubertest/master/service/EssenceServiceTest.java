package kubertest.master.service;

import kubertest.master.BaseTestClassWithPostgreSQL;
import kubertest.master.data.dto.EssenceEmailOffDto;
import kubertest.master.data.dto.FullEssenceDto;
import kubertest.master.data.mapper.EssenceMapper;
import kubertest.master.data.repository.EssenceRepository;
import kubertest.master.service.EssenceService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.NoSuchElementException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Order(2)
public class EssenceServiceTest extends BaseTestClassWithPostgreSQL {

    @Autowired
    private EssenceService essenceService;

    @Autowired
    private EssenceRepository essenceRepository;

    @Autowired
    private EssenceMapper essenceMapper;

    @Test
    @Sql(scripts = "/insert", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void createSomeEntity() {
        FullEssenceDto essenceDto = new FullEssenceDto();
        essenceDto.setId(UUID.fromString("8a9fe7b6-bb38-11ec-8422-0242ac120002"));
        essenceDto.setSomeDate(LocalDate.now());
        essenceDto.setSomeTime(LocalTime.now());
        essenceDto.setEmail("example11@gmail.com");
        essenceDto.setTitle("testTitle");
        assertEquals(essenceRepository.count(), 3);
        essenceService.createSomeEntity(essenceDto);
        assertEquals(essenceRepository.count(), 4);
        assertEquals(essenceRepository.findById(essenceDto.getId()).get().getTitle(), essenceDto.getTitle());
    }

    @Test
    @Sql(scripts = "/insert", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void createSomeEntityEmailExists() {
        FullEssenceDto essenceDto = new FullEssenceDto();
        essenceDto.setId(UUID.fromString("8a9fe7b6-bb38-11ec-8422-0242ac120002"));
        essenceDto.setSomeDate(LocalDate.now());
        essenceDto.setSomeTime(LocalTime.now());
        essenceDto.setEmail("email@gmail.com");
        essenceDto.setTitle("testTitle");
        assertEquals(essenceRepository.count(), 3);
        assertThrows(IllegalArgumentException.class, ()-> {essenceService.createSomeEntity(essenceDto); });
        assertEquals(essenceRepository.count(), 3);
    }

    @Test
    @Sql(scripts = "/insert", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void createSomeEntityIdExists() {
        FullEssenceDto essenceDto = new FullEssenceDto();
        essenceDto.setId(UUID.fromString("00000001-0000-0000-0000-000000000001"));
        essenceDto.setSomeDate(LocalDate.now());
        essenceDto.setSomeTime(LocalTime.now());
        essenceDto.setEmail("email1234@gmail.com");
        essenceDto.setTitle("testTitle");
        assertEquals(essenceRepository.count(), 3);
        assertThrows(IllegalArgumentException.class, ()-> {essenceService.createSomeEntity(essenceDto); });
        assertEquals(essenceRepository.count(), 3);
    }

    @Test
    @Sql(scripts = "/insert", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void createSomeEntityWithoutId() {
        EssenceEmailOffDto essenceDto = new EssenceEmailOffDto
                (LocalDate.now(),LocalTime.now(),"someTitle");
        assertEquals(essenceRepository.count(), 3);
        UUID id = essenceService.createSomeEntityWithoutId(essenceDto);
        assertEquals(essenceRepository.count(), 4);
        assertEquals(essenceRepository.findById(id).get().getTitle(), essenceDto.getTitle());
    }

    @Test
    @Sql(scripts = "/insert", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void findByIdEmailOffDto() {
       assertEquals(essenceService
               .findByIdEmailOffDto
                       (UUID.fromString("00000001-0000-0000-0000-000000000003"))
               .getTitle(), "test_title2");
    }

    @Test
    @Sql(scripts = "/insert", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void findByIdEmailOffDtoException() {
        assertThrows(NoSuchElementException.class, ()-> {
            essenceService.findByIdEmailOffDto(UUID.fromString("00000001-0000-0000-0000-000000000008")); });
    }

    @Test
    @Sql(scripts = "/insert1.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void isValidFullEssenceNullEmail() {
        assertFalse(essenceService
                .isValidFullEssence
                        (essenceMapper.toFullEssenceDto(essenceRepository.findById(UUID.fromString("00000001-0000-0000-0000-000000000003")).get())));
    }

    @Test
    void isValidFullEssenceNull() {
        assertFalse(essenceService
                .isValidFullEssence
                        (new FullEssenceDto()));
    }

    @Test
    void isValidFullEssenceIdNotNull() {
        FullEssenceDto fullEssenceDto = new FullEssenceDto();
        fullEssenceDto.setId(UUID.fromString("00000001-0000-0000-0000-000000000003"));
        fullEssenceDto.setSomeDate(LocalDate.now());
        fullEssenceDto.setSomeTime(LocalTime.now());
        fullEssenceDto.setEmail("123@mail.com");
        assertFalse(essenceService
                .isValidFullEssence
                        (fullEssenceDto));
    }

    @Test
    void isValidFullDateNull() {
        FullEssenceDto fullEssenceDto = new FullEssenceDto();
        fullEssenceDto.setId(UUID.fromString("00000001-0000-0000-0000-000000000003"));
        fullEssenceDto.setSomeTime(LocalTime.now());
        fullEssenceDto.setTitle("oops");
        fullEssenceDto.setEmail("123@mail.com");
        assertFalse(essenceService
                .isValidFullEssence
                        (fullEssenceDto));
    }

    @Test
    void isValidFullEssenceTimeNull() {
        FullEssenceDto fullEssenceDto = new FullEssenceDto();
        fullEssenceDto.setId(UUID.fromString("00000001-0000-0000-0000-000000000003"));
        fullEssenceDto.setSomeDate(LocalDate.now());
        fullEssenceDto.setTitle("oops");
        fullEssenceDto.setEmail("123@mail.com");
        assertFalse(essenceService
                .isValidFullEssence
                        (fullEssenceDto));
    }

    @Test
    void isValidFullEssenceEmailNull() {
        FullEssenceDto fullEssenceDto = new FullEssenceDto();
        fullEssenceDto.setId(UUID.fromString("00000001-0000-0000-0000-000000000003"));
        fullEssenceDto.setSomeDate(LocalDate.now());
        fullEssenceDto.setSomeTime(LocalTime.now());
        fullEssenceDto.setTitle("oops");
        assertFalse(essenceService
                .isValidFullEssence
                        (fullEssenceDto));
    }

    @Test
    void isValidFullEssenceValid() {
        FullEssenceDto fullEssenceDto = new FullEssenceDto();
        fullEssenceDto.setId(UUID.fromString("00000001-0000-0000-0000-000000000003"));
        fullEssenceDto.setSomeDate(LocalDate.now());
        fullEssenceDto.setSomeTime(LocalTime.now());
        fullEssenceDto.setTitle("oops");
        fullEssenceDto.setEmail("123@mail.com");
        assertTrue(essenceService
                .isValidFullEssence
                        (fullEssenceDto));
    }
}