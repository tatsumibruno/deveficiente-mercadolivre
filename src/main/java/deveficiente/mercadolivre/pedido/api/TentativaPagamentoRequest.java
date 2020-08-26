package deveficiente.mercadolivre.pedido.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import deveficiente.mercadolivre.pedido.dominio.Compra;
import deveficiente.mercadolivre.pedido.dominio.CompraRepository;
import deveficiente.mercadolivre.pedido.dominio.StatusPagamento;
import deveficiente.mercadolivre.pedido.dominio.comandos.TentativaPagamentoCommand;

import java.util.UUID;

public interface TentativaPagamentoRequest {

    UUID getIdCompra();

    String getIdPagamento();

    @JsonIgnore
    StatusPagamento getStatusPagamento();

    default TentativaPagamentoCommand comando(CompraRepository compraRepository) {
        Compra compra = compraRepository.findById(getIdCompra())
                .orElseThrow(() -> new IllegalArgumentException(("compra.nao.encontrada")));
        return new TentativaPagamentoCommand(getIdPagamento(), compra, getStatusPagamento());
    }
}
