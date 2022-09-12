package com.haroldo.minhasfinancas.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.haroldo.minhasfinancas.model.entity.Usuario;
import com.haroldo.minhasfinancas.model.repository.UsuarioRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UsuarioServiceImplIntegracaoTest {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @MockBean
    private UsuarioServiceImpl userService;

    String url = "/salvar";

    @Test
    @Order(1)
    void deveObterTodosUsuarios() throws Exception {
        String url = "/autenticar";
        mockMvc.perform(
                MockMvcRequestBuilders.get(url)
        ).andExpect(status().isOk());
    }
    @Test
    @Order(2)
    void shouldCreateaNewTipoServico() throws Exception {

        Usuario servicoTest = new Usuario("lio@","skrrrr", "123");

        String content = objectMapper.writeValueAsString(servicoTest);

        mockMvc.perform(
                post(url)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    @Order(3)
    void deveAdicionarUsurioReturnarValidationExceptionSeNaoForValido() {
        Usuario usuarioTest = new Usuario("Lio","croons@test","097");

        String content = objectMapper.writeValueAsString(usuarioTest);

        mockMvc.perform(
                post(url)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }


}