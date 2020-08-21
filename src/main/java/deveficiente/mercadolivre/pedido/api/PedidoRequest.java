package deveficiente.mercadolivre.pedido.api;

import deveficiente.mercadolivre.pedido.dominio.GatewayPagamento;
import deveficiente.mercadolivre.pedido.dominio.NovaCompra;
import deveficiente.mercadolivre.produto.dominio.Produto;
import deveficiente.mercadolivre.produto.dominio.ProdutoRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.UUID;

@Getter
@ToString
@NoArgsConstructor
public class PedidoRequest {

    @NotNull(message = "{gateway.deve.ser.informado}")
    private GatewayPagamento gatewayPagamento;
    @NotNull(message = "{produto.deve.ser.informado}")
    private UUID idProduto;
    @Positive(message = "{quantidade.deve.ser.maior.um}")
    private int quantidade;

    public NovaCompra comando(ProdutoRepository produtoRepository) {
        Produto produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new IllegalArgumentException("produto.nao.encontrado"));
        return new NovaCompra(gatewayPagamento, produto, quantidade);
    }
}
