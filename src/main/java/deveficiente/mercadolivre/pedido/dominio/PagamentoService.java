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
    public void processar(@Valid TentativaPagamentoCommand tentativaPagamento) {
        tentativaPagamento.executar();
        System.out.println(tentativaPagamento);
    }
}
