package deveficiente.mercadolivre.pedido.api;

import deveficiente.mercadolivre.pedido.dominio.CompraRepository;
import deveficiente.mercadolivre.pedido.dominio.StatusPagamento;
import deveficiente.mercadolivre.pedido.dominio.comandos.TentativaPagamentoCommand;

import java.util.UUID;

public interface TentativaPagamentoRequest {

    UUID getIdCompra();

    String getIdPagamento();

    StatusPagamento getStatusPagamento();

    default TentativaPagamentoCommand comando(CompraRepository compraRepository) {
        return null;
    }
}
