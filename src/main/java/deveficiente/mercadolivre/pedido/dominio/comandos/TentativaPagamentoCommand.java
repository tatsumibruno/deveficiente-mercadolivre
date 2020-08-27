package deveficiente.mercadolivre.pedido.dominio.comandos;

import deveficiente.mercadolivre.pedido.dominio.Compra;
import deveficiente.mercadolivre.pedido.dominio.StatusPagamento;
import lombok.Getter;
import lombok.NonNull;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TentativaPagamentoCommand {
    @NotEmpty
    private String idPagamento;
    @Getter
    @NotNull
    private Compra compra;
    @NotNull
    private StatusPagamento statusPagamento;

    public TentativaPagamentoCommand(@NonNull String idPagamento, @NonNull Compra compra, @NonNull StatusPagamento statusPagamento) {
        this.idPagamento = idPagamento;
        this.compra = compra;
        this.statusPagamento = statusPagamento;
    }

    public void executar() {
        compra.addTentativaPagamento(idPagamento, statusPagamento);
        compra.setStatus(statusPagamento.toStatusCompra());
    }
}
