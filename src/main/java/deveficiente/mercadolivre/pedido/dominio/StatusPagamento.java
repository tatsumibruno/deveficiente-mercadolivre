package deveficiente.mercadolivre.pedido.dominio;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StatusPagamento {
    ERRO(StatusCompra.ERRO_PAGAMENTO),
    SUCESSO(StatusCompra.CONCLUIDA);

    private final StatusCompra statusCompra;

    public StatusCompra toStatusCompra() {
        return this.statusCompra;
    }
}
