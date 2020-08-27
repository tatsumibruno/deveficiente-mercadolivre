package deveficiente.mercadolivre.pedido.api;

import deveficiente.mercadolivre.pedido.api.validators.PagamentoCompraSucessoValidator;
import deveficiente.mercadolivre.pedido.api.validators.PagamentoComPagseguroValidator;
import deveficiente.mercadolivre.pedido.api.validators.PagamentoComPaypalValidator;
import deveficiente.mercadolivre.pedido.dominio.Compra;
import deveficiente.mercadolivre.pedido.dominio.CompraRepository;
import deveficiente.mercadolivre.pedido.dominio.ProcessoPosPagamento;
import deveficiente.mercadolivre.pedido.dominio.comandos.TentativaPagamentoCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pagamento")
public class PagamentoController {

    private final CompraRepository compraRepository;
    private final PagamentoComPagseguroValidator pagseguroValidator;
    private final PagamentoComPaypalValidator paypalValidator;
    private final PagamentoCompraSucessoValidator pagamentoCompraSucessoValidator;
    private final Collection<ProcessoPosPagamento> processosPosPagamento;

    @InitBinder("pagamentoPaypalRequest")
    public void initBinderPaypal(WebDataBinder binder) {
        binder.addValidators(paypalValidator, pagamentoCompraSucessoValidator);
    }

    @InitBinder("pagamentoPagseguroRequest")
    public void initBinderPagseguro(WebDataBinder binder) {
        binder.addValidators(pagseguroValidator, pagamentoCompraSucessoValidator);
    }

    @Transactional
    @PostMapping(path = "/paypal")
    public ResponseEntity<Void> pagamentoPaypal(@Valid @RequestBody PagamentoPaypalRequest pagamento) {
        TentativaPagamentoCommand comando = pagamento.comando(compraRepository);
        processar(comando);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PostMapping(path = "/pagseguro")
    public ResponseEntity<Void> pagamentoPagseguro(@Valid @RequestBody PagamentoPagseguroRequest pagamento) {
        TentativaPagamentoCommand comando = pagamento.comando(compraRepository);
        processar(comando);
        return ResponseEntity.noContent().build();
    }

    private void processar(@Valid TentativaPagamentoCommand tentativaPagamento) {
        tentativaPagamento.executar();
        Compra compra = tentativaPagamento.getCompra();
        processosPosPagamento.forEach(processo -> {
            if (processo.deveProcessar(compra)) {
                processo.processar(compra);
            }
        });
    }
}
