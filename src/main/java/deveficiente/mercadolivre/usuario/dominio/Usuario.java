package deveficiente.mercadolivre.usuario.dominio;

import deveficiente.mercadolivre.comum.infra.converters.DadoCriptografadoConverter;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@ToString(exclude = "senha")
@EqualsAndHashCode(of = "login")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Entity
@Table(name = "usuario", uniqueConstraints = @UniqueConstraint(name = "usuario_login_un", columnNames = "login"))
public class Usuario implements UserDetails {

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

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }
}
