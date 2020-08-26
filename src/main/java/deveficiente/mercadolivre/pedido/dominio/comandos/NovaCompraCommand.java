package deveficiente.mercadolivre.pedido.dominio.comandos;

import deveficiente.mercadolivre.pedido.dominio.GatewayPagamento;
import deveficiente.mercadolivre.produto.dominio.Produto;
import lombok.NonNull;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Value
public class NovaCompraCommand {

    @NotNull GatewayPagamento gatewayPagamento;
    @NotNull Produto produto;
    @Positive int quantidade;

    public NovaCompraCommand(@NonNull GatewayPagamento gatewayPagamento, @NonNull Produto produto, int quantidade) {
        this.gatewayPagamento = gatewayPagamento;
        this.produto = produto;
        this.quantidade = quantidade;
    }
}
