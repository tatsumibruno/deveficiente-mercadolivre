package deveficiente.mercadolivre.pedido.api;

import deveficiente.mercadolivre.pedido.dominio.Compra;
import deveficiente.mercadolivre.pedido.dominio.CompraRepository;
import deveficiente.mercadolivre.pedido.dominio.StatusPagamento;
import deveficiente.mercadolivre.pedido.dominio.comandos.TentativaPagamentoCommand;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@ToString
public class PagamentoPagseguroRequest implements TentativaPagamentoRequest {

    @NotEmpty
    private String idPagamento;
    @NotNull
    private PagamentoPagseguroStatusRequest status;
    @NotNull
    private UUID idCompra;

    @Override
    public StatusPagamento getStatusPagamento() {
        return status.getStatusCompra();
    }

    @Override
    public TentativaPagamentoCommand comando(CompraRepository compraRepository) {
        Compra compra = compraRepository.findById(idCompra)
                .orElseThrow(() -> new IllegalArgumentException("compra.nao.encontrada"));
        return new TentativaPagamentoCommand(idPagamento, compra, status.getStatusCompra());
    }

    private enum PagamentoPagseguroStatusRequest {
        SUCESSO(StatusPagamento.SUCESSO),
        ERRO(StatusPagamento.ERRO);

        @Getter
        private final StatusPagamento statusCompra;

        PagamentoPagseguroStatusRequest(StatusPagamento statusCompra) {
            this.statusCompra = statusCompra;
        }
    }
}
