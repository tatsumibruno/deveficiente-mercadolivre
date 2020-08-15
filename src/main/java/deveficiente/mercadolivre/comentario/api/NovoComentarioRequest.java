package deveficiente.mercadolivre.comentario.api;

import deveficiente.mercadolivre.comentario.dominio.Comentario;
import deveficiente.mercadolivre.comentario.dominio.NotaProduto;
import deveficiente.mercadolivre.produto.dominio.Produto;
import deveficiente.mercadolivre.produto.dominio.ProdutoRepository;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NovoComentarioRequest {
    @Min(1)
    @Max(5)
    private int nota;
    @NotEmpty
    private String titulo;
    @NotEmpty
    @Size(max = 500)
    private String descricao;
    @NotNull
    private UUID idProduto;

    public Comentario entidade(ProdutoRepository produtoRepository) {
        Produto produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new IllegalArgumentException("produto.nao.encontrado"));
        return new Comentario(NotaProduto.from(nota), titulo, descricao, produto);
    }
}
