package unitario;

import com.github.javafaker.Faker;
import com.haroldo.minhasfinancas.model.entity.Usuario;
import com.haroldo.minhasfinancas.service.impl.UsuarioServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Locale;


@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UsuaroServiceImplTest {

    private  UsuarioServiceImpl usuarioService;
    private final Faker faker = new Faker(new Locale("pt-br"));

    public UsuaroServiceImplTest(UsuarioServiceImpl usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Test
    void store() {
        Usuario usuario = new Usuario();

        usuario.setNome(faker.name().name());
        usuario.setEmail(faker.internet().emailAddress());
        usuario.setSenha(faker.random().hex());

        Usuario resultdb = usuarioService.salvarUsuario(usuario);
        Assertions.assertEquals(usuario, resultdb);
    }

}
