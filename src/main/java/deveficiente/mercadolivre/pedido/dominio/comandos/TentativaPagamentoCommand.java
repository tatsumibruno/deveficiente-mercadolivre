package deveficiente.mercadolivre.pedido.dominio.comandos;

import deveficiente.mercadolivre.pedido.dominio.Compra;
import deveficiente.mercadolivre.pedido.dominio.StatusPagamento;
import lombok.NonNull;
import lombok.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Value
public class TentativaPagamentoCommand {
    @NotEmpty String idPagamento;
    @NotNull Compra compra;
    @NotNull StatusPagamento statusPagamento;

    public TentativaPagamentoCommand(@NonNull String idPagamento, @NonNull Compra compra, @NonNull StatusPagamento statusPagamento) {
        this.idPagamento = idPagamento;
        this.compra = compra;
        this.statusPagamento = statusPagamento;
    }

    public void executar() {
        compra.addTentativaPagamento(statusPagamento);
        compra.setStatus(statusPagamento.toStatusCompra());
    }
}
