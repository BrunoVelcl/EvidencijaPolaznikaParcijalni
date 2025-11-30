package com.algebra.evidencijapolaznika.controller;


import com.algebra.evidencijapolaznika.dal.entity.ProgramObrazovanja;
import com.algebra.evidencijapolaznika.dal.repository.ProgramObrazovanjaRepository;
import com.algebra.evidencijapolaznika.dto.Request.LoginRequestDTO;
import com.algebra.evidencijapolaznika.dto.Request.ProgramObrazovanjaCreateRequestDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class ProgramObrazovanjaControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuthController authController;

    @Autowired
    private ProgramObrazovanjaRepository programObrazovanjaRepository;

    private String accessToken;

    private static final String API_PROGRAM = "/api/program";
    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";
    private static final String NEW_LINE = System.lineSeparator();

    @BeforeEach
    void setUp() {

        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setUsername("admin");
        loginRequestDTO.setPassword("admin");

        if (accessToken == null) {
            var rv = this.authController.login(loginRequestDTO);
            this.accessToken = rv.getBody().getAccessToken();
        }
    }

    static Stream<Arguments> courseStream() {
        return Stream.of(
                Arguments.arguments(0, "Java-Backend-Programiranje", 50),
                Arguments.arguments(11, "Elektrotehnika", 40),
                Arguments.arguments(20, "Kemijski-Laboratorij", 35),
                Arguments.arguments(28, "Radionica-Robotike", 30)
        );
    }

    @ParameterizedTest
    @MethodSource("courseStream")
    void findAll_WhenCalled_ReturnsAllCourses(int index, String name, int csvet) throws Exception {
        this.mockMvc
                .perform(get(API_PROGRAM)
                        .header(AUTHORIZATION, BEARER + this.accessToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(29)))
                .andExpect(jsonPath("$[" + index + "].naziv", is(name)))
                .andExpect(jsonPath("$[" + index + "].csvet", is(csvet)));
    }

    @ParameterizedTest
    @MethodSource("courseStream")
    void findById_WhenCalledWithValidId_ReturnsExpectedCourse(int index, String name, int csvet) throws Exception {
        this.mockMvc
                .perform(get(API_PROGRAM + "/{id}", index + 1)
                        .header(AUTHORIZATION, BEARER + this.accessToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("naziv", is(name)))
                .andExpect(jsonPath("csvet", is(csvet)));
    }

    @Test
    void findById_WhenCalledWithInvalidId_ReturnsNoContent() throws Exception {
        this.mockMvc
                .perform(get(API_PROGRAM + "/{id}", 1474)
                        .header(AUTHORIZATION, BEARER + this.accessToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    private final ProgramObrazovanja programObrazovanja = new ProgramObrazovanja(30, "Neurokirurgija", 150);
    private static final String JSON_BODY_TEMPLATE =
            "{" + NEW_LINE
                    + "\"naziv\": \"%s\"," + NEW_LINE
                    + "\"csvet\": \"%s\"" + NEW_LINE
                    + "}";

    @Test
    void create_WhenCalledWithValidInput_PersistToDatabase() throws Exception {
        String body = String.format(JSON_BODY_TEMPLATE, programObrazovanja.getNaziv(), programObrazovanja.getCsvet());
        this.mockMvc
                .perform(post(API_PROGRAM)
                        .header(AUTHORIZATION, BEARER + this.accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated());

        var rv = this.programObrazovanjaRepository.findById(programObrazovanja.getId());
        assertEquals(programObrazovanja, rv.get());
    }

    @Test
    void create_WhenCalledWithEmptyName_ReturnsBadRequest() throws Exception {
        String body =
                "{" + NEW_LINE
                        + "\"naziv\": null," + NEW_LINE
                        + "\"csvet\": \"50\"" + NEW_LINE
                        + "}";

        this.mockMvc
                .perform(post(API_PROGRAM)
                        .header(AUTHORIZATION, BEARER + this.accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest());
    }

    @Test
    void create_WhenCalledWithEmptyCsvet_ReturnsBadRequest() throws Exception {
        String body =
                "{" + NEW_LINE
                        + "\"naziv\": \"Novi obrazovni program\"," + NEW_LINE
                        + "\"csvet\": null" + NEW_LINE
                        + "}";

        this.mockMvc
                .perform(post(API_PROGRAM)
                        .header(AUTHORIZATION, BEARER + this.accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest());
    }

    @Test
    void create_WhenCalledWithBlankName_ReturnsBadRequest() throws Exception {
        String body = String.format(JSON_BODY_TEMPLATE, "", this.programObrazovanja.getCsvet());

        this.mockMvc
                .perform(post(API_PROGRAM)
                        .header(AUTHORIZATION, BEARER + this.accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest());
    }

    @Test
    void create_WhenCalledWithNegativeCsvet_ReturnsBadRequest() throws Exception {
        String body = String.format(JSON_BODY_TEMPLATE, this.programObrazovanja.getNaziv(), -1);

        this.mockMvc
                .perform(post(API_PROGRAM)
                        .header(AUTHORIZATION, BEARER + this.accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest());
    }

}
