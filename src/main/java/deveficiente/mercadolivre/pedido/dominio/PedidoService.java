package deveficiente.mercadolivre.pedido.dominio;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
@RequiredArgsConstructor
public class PedidoService {

    private final CompraRepository compraRepository;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Compra realizar(@Valid NovaCompra novaCompra) {
        Compra compra = new Compra(novaCompra.getGatewayPagamento(),
                novaCompra.getProduto(),
                novaCompra.getQuantidade());
        compraRepository.save(compra);
        return compra;
    }
}
