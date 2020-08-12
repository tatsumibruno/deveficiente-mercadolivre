package deveficiente.mercadolivre.seguranca.api;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString(exclude = "senha")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginRequest {
    private String usuario;
    private String senha;
}
