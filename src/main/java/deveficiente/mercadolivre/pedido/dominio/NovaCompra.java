package deveficiente.mercadolivre.pedido.dominio;

import deveficiente.mercadolivre.produto.dominio.Produto;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@ToString
public class NovaCompra {

    @NotNull
    private GatewayPagamento gatewayPagamento;
    @NotNull
    private Produto produto;
    @Positive
    private int quantidade;

    public NovaCompra(@NonNull GatewayPagamento gatewayPagamento, @NonNull Produto produto, int quantidade) {
        this.gatewayPagamento = gatewayPagamento;
        this.produto = produto;
        this.quantidade = quantidade;
    }
}
