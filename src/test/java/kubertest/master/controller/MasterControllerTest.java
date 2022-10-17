package kubertest.master.controller;

import kubertest.master.BaseTestClassWithPostgreSQL;
import kubertest.master.data.dto.EssenceEmailOffDto;
import kubertest.master.service.EssenceService;
import kubertest.master.service.InfoService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@Order(6)
public class MasterControllerTest extends BaseTestClassWithPostgreSQL {
    @Autowired
    private EssenceService essenceService;
    @Autowired
    private InfoService infoService;
    private final String BASE = "/api/latest/web/Master";

    @SneakyThrows
    @Test
    void createSomeEntity() {
        mockMvc.perform(MockMvcRequestBuilders.post(BASE+"/entity")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        asJsonString(
                                new EssenceEmailOffDto(
                                        LocalDate.now(),
                                        LocalTime.now(),
                                        "title"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isString());
    }

    @SneakyThrows
    @Test
    @Sql(scripts = "/insert", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void findById() {
        mockMvc.perform(MockMvcRequestBuilders.get(BASE+"/00000001-0000-0000-0000-000000000001"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("test_title"))
                .andExpect(jsonPath("$.someDate").value("2021-06-01"))
                .andExpect(jsonPath("$.someTime").value("08:00:00"));
    }

    @SneakyThrows
    @Test
    void getInfo() {
        mockMvc.perform(MockMvcRequestBuilders.get(BASE+"/info"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.javaVersion").value(Runtime.version().toString()))
                .andExpect(jsonPath("$.localDate").value(LocalDate.now().toString()));
    }
}