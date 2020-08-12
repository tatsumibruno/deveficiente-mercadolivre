package deveficiente.mercadolivre.seguranca.api;

import deveficiente.mercadolivre.seguranca.dominio.LoginService;
import deveficiente.mercadolivre.seguranca.infra.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/login")
public class LoginController {

    private final LoginService loginService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest request) {
        String token = loginService.logar(authenticationManager, jwtTokenUtil, request.getUsuario(), request.getSenha());
        return ResponseEntity.ok(token);
    }
}
