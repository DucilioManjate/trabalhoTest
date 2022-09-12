package com.haroldo.minhasfinancas.service.impl;

import com.github.javafaker.Faker;
import com.haroldo.minhasfinancas.exception.ErroAutenticacaoException;
import com.haroldo.minhasfinancas.exception.RegraNegocioException;
import com.haroldo.minhasfinancas.model.entity.Usuario;
import com.haroldo.minhasfinancas.model.repository.UsuarioRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.Id;
import java.util.Locale;
import java.util.Optional;

import static javax.swing.text.html.parser.DTDConstants.ID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UsuarioServiceImpl.class, UsuarioRepository.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UsuarioServiceImplTest {

    @MockBean
    UsuarioRepository usuarioRepository;

    @Autowired
    @InjectMocks
    private UsuarioServiceImpl usuarioService;
    private final Faker faker = new Faker(new Locale("pt-br"));

    @Test
    public void deveriaSalvarUmUsuarioComSucesso() {
        Usuario usuario = new Usuario();

        usuario.setNome(faker.name().name());
        usuario.setEmail(faker.internet().emailAddress());
        usuario.setSenha(faker.random().hex());

        when(usuarioRepository.existsByEmail(any())).thenReturn(false);
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario resultdb = usuarioService.salvarUsuario(usuario);
        assertEquals(usuario, resultdb);
    }

    @Test
    public void naoDeveriaSalvarUsuarioQuandoEmailInformadoJaTerSidoCadastrado() {
        Usuario usuario = new Usuario();
        usuario.setEmail(faker.internet().emailAddress());
        when(usuarioRepository.existsByEmail(any())).thenReturn(true);
        Assertions.assertThrows(RegraNegocioException.class, ()-> usuarioService.salvarUsuario(usuario));
    }

    @Test
    public void erroQuandoNaoForEncotradaUsuario() {
      when(usuarioRepository.findByEmail("lio@hmail.com")).thenThrow(new ErroAutenticacaoException("usuario nao encontrado"));
      try{
          usuarioService.autenticar("lio","1233");
      }catch (Exception ex) {
          assertEquals(ErroAutenticacaoException.class,ex.getClass());
      }
}
    @Test
    public void obterUsuarioPorId() {
        Usuario usuario = new Usuario();

        usuario.setNome(faker.name().name());
        usuario.setEmail(faker.internet().emailAddress());
        usuario.setSenha(faker.random().hex());

        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);


        Usuario usuario2= usuarioRepository.save(usuario);

        when(usuarioRepository.findById(usuario2.getId())).thenReturn(Optional.of(usuario2));

        Optional<Usuario>  usuario1 = usuarioService.obterPorId(usuario2.getId());
        assertTrue(usuario1.isPresent());

    }
}