package deveficiente.mercadolivre.produto.api;

import deveficiente.mercadolivre.categoria.dominio.Categoria;
import deveficiente.mercadolivre.categoria.dominio.CategoriaRepository;
import deveficiente.mercadolivre.produto.dominio.CaracteristicaProduto;
import deveficiente.mercadolivre.produto.dominio.FotoProduto;
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

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NovoProdutoRequest {
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
    private List<CaracteristicaProduto> caracteristicas;
    @NotNull
    @Size(min = 1, max = 10)
    private List<FotoProduto> fotos;
    @NotEmpty
    @Size(min = 1, max = 1_000)
    private String descricao;
    @NotNull
    private UUID idCategoria;

    public Produto entidade(CategoriaRepository categoriaRepository) {
        Categoria categoria = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new IllegalArgumentException("categoria.nao.encontrada"));
        return Produto.builder()
                .nome(nome)
                .valor(valor)
                .quantidadeDisponivel(quantidadeDisponivel)
                .caracteristicas(caracteristicas)
                .fotos(fotos)
                .descricao(descricao)
                .categoria(categoria)
                .build();
    }
}
