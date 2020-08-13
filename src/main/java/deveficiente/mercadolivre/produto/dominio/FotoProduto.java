package deveficiente.mercadolivre.produto.dominio;

import lombok.*;

import javax.persistence.Embeddable;

@Getter
@ToString
@Embeddable
@AllArgsConstructor
@EqualsAndHashCode(of = "titulo")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class FotoProduto {
    private String titulo;
    private String url;
}
