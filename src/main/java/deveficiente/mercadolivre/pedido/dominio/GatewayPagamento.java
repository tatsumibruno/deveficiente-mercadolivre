package deveficiente.mercadolivre.pedido.dominio;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter(AccessLevel.PRIVATE)
public enum GatewayPagamento {
    PAYPAL("https://paypal.com/%s?redirectUrl=http://localhost:8081/api/v1/pagamento/paypal"),
    PAGSEGURO("https://pagseguro.com?returnId=%s&redirectUrl=http://localhost:8081/api/v1/pagamento/pagseguro");

    private final String link;

    public String gerarLinkPagamento(UUID id) {
        String idCompra = id.toString();
        return String.format(this.getLink(), idCompra, idCompra);
    }
}
