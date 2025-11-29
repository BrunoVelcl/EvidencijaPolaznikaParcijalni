package com.algebra.evidencijapolaznika.controller;

import com.algebra.evidencijapolaznika.dal.entity.Polaznik;
import com.algebra.evidencijapolaznika.dal.repository.PolaznikRepository;
import com.algebra.evidencijapolaznika.dto.Request.LoginRequestDTO;
import com.algebra.evidencijapolaznika.dto.Response.JwtResponseDTO;
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
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;


import java.util.stream.Stream;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class PolaznikControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuthController authController;

    @Autowired
    private PolaznikRepository polaznikRepository;

    private static final String BEARER = "Bearer ";
    private static final String AUTHORIZATION = "Authorization";
    private static final String API_PATH = "/api/polaznik";
    private String accessToken;

    @BeforeEach
    void setUp() {
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setUsername("admin");
        loginRequestDTO.setPassword("admin");

        if (this.accessToken == null) {
            ResponseEntity<JwtResponseDTO> response = this.authController.login(loginRequestDTO);
            this.accessToken = response.getBody().getAccessToken();
        }
    }

    static Stream<Arguments> studentStream() {
        return Stream.of(
                Arguments.arguments(0, "Lara", "Petrović"),
                Arguments.arguments(12, "Kaja", "Domrek"),
                Arguments.arguments(28, "Tibor", "Senarić")
        );
    }

    @ParameterizedTest
    @MethodSource("studentStream")
    void findAll_WhenCalled_ReturnsAllStudents(int index, String ime, String prezime) throws Exception {
        this.mockMvc
                .perform(get(API_PATH)
                        .header(AUTHORIZATION, BEARER + this.accessToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(29)))
                .andExpect(jsonPath("$[" + index + "].ime", is(ime)))
                .andExpect(jsonPath("$[" + index + "].prezime", is(prezime)));
    }

    ;


    @ParameterizedTest
    @MethodSource("studentStream")
    void findById_WhenCalledWithValidId_ReturnsExpectedStudent(int index, String ime, String prezime) throws Exception {
        this.mockMvc
                .perform(get(API_PATH + "/{id}", index + 1)
                        .header(AUTHORIZATION, BEARER + this.accessToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("ime", is(ime)))
                .andExpect(jsonPath("prezime", is(prezime)));
    }

    private static final Polaznik POLAZNIK = new Polaznik(30, "Marko", "Markić");
    private static final String JSON_BODY_TEMPLATE = """
            {
                "ime": "%s",
                "prezime": "%s"
            }
            """;

    @Test
    void findById_WhenCalledWithInvalidId_ReturnsBadRequest() throws Exception {
        this.mockMvc
                .perform(get(API_PATH + "/{id}", 128)
                        .header(AUTHORIZATION, BEARER + this.accessToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void create_WhenCalledWithCorrectInput_InsertsRow() throws Exception {
        String body = String.format(JSON_BODY_TEMPLATE, POLAZNIK.getIme(), POLAZNIK.getPrezime());
        this.mockMvc
                .perform(post(API_PATH)
                        .header(AUTHORIZATION, BEARER + this.accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated());

        var rv = this.polaznikRepository.findById(POLAZNIK.getId());
        assertEquals(POLAZNIK, rv.get());
    }

    @Test
    void create_WhenCalledWithEmptyFirstName_ReturnsBadRequest() throws Exception {
        String body = String.format(JSON_BODY_TEMPLATE, "", POLAZNIK.getIme());

        this.mockMvc
                .perform(post(API_PATH)
                        .header(AUTHORIZATION, BEARER + this.accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest());
    }

    @Test
    void create_WhenCalledWithEmptyLastName_ReturnsBadRequest() throws Exception {
        String body = String.format(JSON_BODY_TEMPLATE, POLAZNIK.getIme(), "");

        this.mockMvc
                .perform(post(API_PATH)
                        .header(AUTHORIZATION, BEARER + this.accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest());
    }

    @Test
    void create_WhenCalledWithBlankLastName_ReturnsBadRequest() throws Exception {
        String body = String.format(JSON_BODY_TEMPLATE, POLAZNIK.getIme(), "   ");

        this.mockMvc
                .perform(post(API_PATH)
                        .header(AUTHORIZATION, BEARER + this.accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest());
    }

    @Test
    void create_WhenCalledWithBlankFirstName_ReturnsBadRequest() throws Exception {
        String body = String.format(JSON_BODY_TEMPLATE, "     ", POLAZNIK.getPrezime());

        this.mockMvc
                .perform(post(API_PATH)
                        .header(AUTHORIZATION, BEARER + this.accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest());
    }
}
