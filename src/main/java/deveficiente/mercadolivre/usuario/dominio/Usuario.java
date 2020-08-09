package deveficiente.mercadolivre.usuario.dominio;

import deveficiente.mercadolivre.comum.infra.converters.DadoCriptografadoConverter;
import lombok.*;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@ToString(exclude = "senha")
@EqualsAndHashCode(of = "login")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Usuario {

    @Id
    @GeneratedValue
    private UUID id;
    @NotNull
    @Email
    @NotEmpty
    private String login;
    @NotNull
    @NotEmpty
    @Convert(converter = DadoCriptografadoConverter.class)
    private String senha;
    @Past
    @NotNull
    private LocalDateTime criadoEm;

    public Usuario(@NonNull String login, @NonNull String senha, @NonNull LocalDateTime criadoEm) {
        this.login = login;
        this.senha = senha;
        this.criadoEm = criadoEm;
    }
}
