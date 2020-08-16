package deveficiente.mercadolivre.seguranca.infra;

import deveficiente.mercadolivre.usuario.dominio.Usuario;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SecurityAuditorAware implements AuditorAware<Usuario> {

    @Override
    public Optional<Usuario> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return Optional.ofNullable(authentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .map(Usuario.class::cast);
    }
}
