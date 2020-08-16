package deveficiente.mercadolivre.pergunta.api;

import deveficiente.mercadolivre.pergunta.dominio.Pergunta;
import deveficiente.mercadolivre.produto.dominio.Produto;
import deveficiente.mercadolivre.produto.dominio.ProdutoRepository;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NovaPerguntaRequest {

    @NotEmpty
    private String titulo;
    @NotEmpty
    private String descricao;
    @NotNull
    private UUID idProduto;

    public Pergunta dominio(ProdutoRepository produtoRepository) {
        Produto produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new IllegalArgumentException("produto.nao.encontrado"));
        return new Pergunta(titulo, descricao, produto);
    }
}
