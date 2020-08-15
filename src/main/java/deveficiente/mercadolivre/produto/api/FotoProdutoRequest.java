package deveficiente.mercadolivre.produto.api;

import deveficiente.mercadolivre.produto.dominio.FotoProduto;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FotoProdutoRequest {
    @NotEmpty
    private String titulo;
    @URL
    @NotEmpty
    private String url;

    public FotoProduto entidade() {
        return new FotoProduto(titulo, url);
    }
}
