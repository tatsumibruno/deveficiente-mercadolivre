package deveficiente.mercadolivre.produto.api;

import deveficiente.mercadolivre.produto.dominio.FotoProduto;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FotoProdutoDTO {
    private String titulo;
    private String url;

    public static FotoProdutoDTO from(FotoProduto fotoProduto) {
        return new FotoProdutoDTO(fotoProduto.getTitulo(), fotoProduto.getUrl());
    }
}
