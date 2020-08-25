package deveficiente.mercadolivre.pedido.dominio;

import deveficiente.mercadolivre.pedido.dominio.comandos.TentativaPagamentoCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
@RequiredArgsConstructor
public class PagamentoService {

    private final CompraRepository compraRepository;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void processar(@Valid TentativaPagamentoCommand pagamento) {
        Compra compra = compraRepository.findById(pagamento.getIdCompra())
                .orElseThrow(() -> new IllegalArgumentException("compra.nao.encontrada"));
        System.out.println(compra);
        System.out.println(pagamento);
    }
}
