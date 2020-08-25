package deveficiente.mercadolivre.pedido.dominio.comandos;

import deveficiente.mercadolivre.pedido.dominio.Compra;
import deveficiente.mercadolivre.pedido.dominio.StatusPagamento;
import lombok.NonNull;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ToString
public class TentativaPagamentoCommand {
    @NotEmpty
    private final String idPagamento;
    @NotNull
    private final Compra compra;
    @NotNull
    private final StatusPagamento statusPagamento;

    public TentativaPagamentoCommand(@NonNull String idPagamento, @NonNull Compra compra, @NonNull StatusPagamento statusPagamento) {
        this.idPagamento = idPagamento;
        this.compra = compra;
        this.statusPagamento = statusPagamento;
    }
}
