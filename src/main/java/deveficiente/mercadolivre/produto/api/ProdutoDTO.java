package deveficiente.mercadolivre.produto.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import deveficiente.mercadolivre.produto.dominio.Produto;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Getter
@ToString
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProdutoDTO {
    private UUID id;
    private String nome;
    private BigDecimal valor;
    private int quantidadeDisponivel;
    private List<CaracteristicaProdutoDTO> caracteristicas = new ArrayList<>();
    private List<FotoProdutoDTO> fotos = new ArrayList<>();
    private String descricao;
    private String categoria;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime criadoEm;

    public static ProdutoDTO from(Produto produto) {
        return ProdutoDTO.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .valor(produto.getValor())
                .quantidadeDisponivel(produto.getQuantidadeDisponivel())
                .caracteristicas(produto.getCaracteristicas().stream().map(CaracteristicaProdutoDTO::from).collect(toList()))
                .fotos(produto.getFotos().stream().map(FotoProdutoDTO::from).collect(toList()))
                .descricao(produto.getDescricao())
                .categoria(produto.getNomeCategoria())
                .criadoEm(produto.getDataCriacao())
                .build();
    }
}
