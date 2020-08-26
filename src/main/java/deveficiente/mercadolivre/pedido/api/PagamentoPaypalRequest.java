package deveficiente.mercadolivre.pedido.api;

import deveficiente.mercadolivre.pedido.dominio.StatusPagamento;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@ToString
public class PagamentoPaypalRequest implements TentativaPagamentoRequest {

    @NotEmpty
    private String idPagamento;
    @NotNull
    @Min(0)
    @Max(1)
    private Integer status;
    @NotNull
    private UUID idCompra;

    @Override
    public UUID getIdCompra() {
        return idCompra;
    }

    @Override
    public String getIdPagamento() {
        return idPagamento;
    }

    @Override
    public StatusPagamento getStatusPagamento() {
        return status == 1 ? StatusPagamento.SUCESSO : StatusPagamento.ERRO;
    }
}
