package deveficiente.mercadolivre.usuario.api;

import deveficiente.mercadolivre.comum.api.validators.Unique;
import deveficiente.mercadolivre.usuario.dominio.Usuario;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@ToString(exclude = "senha")
@EqualsAndHashCode(of = "login")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NovoUsuarioRequest {

    @Email
    @NotNull
    @Unique(field = "login", targetClass = Usuario.class, message = "{NovoUsuarioRequest.Unique.message}")
    private String login;
    @NotNull
    @Size(min = 6, max = 50)
    private String senha;
    @Past
    @NotNull
    private LocalDateTime criadoEm;

    public Usuario modelo() {
        return new Usuario(login, senha, criadoEm);
    }
}
