package kubertest.master.controller;

import kubertest.master.BaseTestClassWithPostgreSQL;
import kubertest.master.service.EssenceService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@Order(5)
public class InternalControllerTest extends BaseTestClassWithPostgreSQL {
    @Autowired
    private EssenceService essenceService;
    private final String BASE = "/api/latest/internal/Master";

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
}