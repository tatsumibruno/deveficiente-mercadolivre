package deveficiente.mercadolivre.seguranca.dominio;

import deveficiente.mercadolivre.seguranca.infra.JwtTokenUtil;
import deveficiente.mercadolivre.usuario.dominio.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public String logar(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, String usuario, String senha) {
        UserDetails userDetails = loadUserByUsername(usuario);
        authenticate(authenticationManager, usuario, senha);
        return jwtTokenUtil.generateToken(userDetails);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return usuarioRepository.findByLogin(username)
                .orElseThrow(() -> new IllegalArgumentException("usuario.nao.encontrado"));
    }

    private void authenticate(AuthenticationManager authenticationManager, String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            log.error("Usuário desabilitado.", e);
            throw new IllegalArgumentException("usuario.desabilitado");
        } catch (BadCredentialsException e) {
            log.error("Usuário ou senha inválidos.", e);
            throw new IllegalArgumentException("credenciais.invalidas");
        }
    }
}
