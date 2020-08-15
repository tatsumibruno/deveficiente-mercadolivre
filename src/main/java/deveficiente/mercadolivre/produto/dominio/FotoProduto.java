package deveficiente.mercadolivre.produto.dominio;

import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;

@Getter
@ToString
@Embeddable
@EqualsAndHashCode(of = "titulo")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class FotoProduto {
    @NotEmpty
    private String titulo;
    @URL
    @NotEmpty
    private String url;

    public FotoProduto(@NonNull String titulo, @NonNull String url) {
        this.titulo = titulo;
        this.url = url;
    }
}
