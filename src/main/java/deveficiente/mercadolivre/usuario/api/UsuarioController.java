package deveficiente.mercadolivre.usuario.api;

import deveficiente.mercadolivre.usuario.dominio.Usuario;
import deveficiente.mercadolivre.usuario.dominio.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody @Valid NovoUsuarioRequest novoUsuario) {
        Usuario usuario = novoUsuario.entidade();
        usuarioRepository.save(usuario);
        return ResponseEntity.ok(novoUsuario.getLogin());
    }
}
