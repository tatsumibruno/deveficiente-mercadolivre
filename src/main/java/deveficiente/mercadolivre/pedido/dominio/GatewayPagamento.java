package deveficiente.mercadolivre.pedido.dominio;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter(AccessLevel.PRIVATE)
public enum GatewayPagamento {
    PAYPAL("https://paypal.com/%s?redirectUrl=" + GatewayPagamento.ENDPOINT_CONFIRMAR_PAGAMENTO),
    PAGSEGURO("https://pagseguro.com?returnId=%s&redirectUrl=http://localhost:8081/api/v1/pedido/%s/pagamento" + GatewayPagamento.ENDPOINT_CONFIRMAR_PAGAMENTO);

    private static final String ENDPOINT_CONFIRMAR_PAGAMENTO = "http://localhost:8081/api/v1/pedido/%s/pagamento";
    private final String link;

    public String gerarLinkPagamento(UUID id) {
        String idCompra = id.toString();
        return String.format(this.getLink(), idCompra, idCompra);
    }
}
