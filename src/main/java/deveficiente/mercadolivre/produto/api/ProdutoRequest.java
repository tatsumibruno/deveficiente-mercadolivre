package deveficiente.mercadolivre.produto.api;

import deveficiente.mercadolivre.categoria.dominio.Categoria;
import deveficiente.mercadolivre.categoria.dominio.CategoriaRepository;
import deveficiente.mercadolivre.produto.dominio.Produto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProdutoRequest {
    @NotEmpty
    private String nome;
    @NotNull
    @Positive
    private BigDecimal valor;
    @NotNull
    @Positive
    private int quantidadeDisponivel;
    @NotNull
    @Size(min = 3, max = 50)
    private List<CaracteristicaProdutoRequest> caracteristicas;
    @NotEmpty
    @Size(min = 1, max = 1_000)
    private String descricao;
    @NotNull
    private UUID idCategoria;

    public Produto modelo(CategoriaRepository categoriaRepository) {
        Categoria categoria = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new IllegalArgumentException("categoria.nao.encontrada"));
        return Produto.builder()
                .nome(nome)
                .valor(valor)
                .quantidadeDisponivel(quantidadeDisponivel)
                .caracteristicas(caracteristicas.stream().map(CaracteristicaProdutoRequest::modelo).collect(Collectors.toSet()))
                .descricao(descricao)
                .categoria(categoria)
                .build();
    }
}
