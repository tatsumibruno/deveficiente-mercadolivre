package deveficiente.mercadolivre.pedido.api;

import deveficiente.mercadolivre.pedido.dominio.StatusPagamento;
import lombok.Getter;

public enum PagamentoPagseguroStatusRequest {
    SUCESSO(StatusPagamento.SUCESSO),
    ERRO(StatusPagamento.ERRO);

    @Getter
    private final StatusPagamento statusCompra;

    PagamentoPagseguroStatusRequest(StatusPagamento statusCompra) {
        this.statusCompra = statusCompra;
    }
}
