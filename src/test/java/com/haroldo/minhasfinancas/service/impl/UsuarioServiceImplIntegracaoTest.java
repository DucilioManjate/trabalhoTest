package com.haroldo.minhasfinancas.service.impl;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.common.Json;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.haroldo.minhasfinancas.api.dto.UsuarioDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.http.ResponseEntity.ok;


public class UsuarioServiceImplIntegracaoTest {

    @Autowired
    private UsuarioServiceImpl usuarioService;




}