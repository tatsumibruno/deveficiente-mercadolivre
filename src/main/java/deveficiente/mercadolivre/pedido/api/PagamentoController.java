package deveficiente.mercadolivre.pedido.api;

import deveficiente.mercadolivre.pedido.api.validators.PagamentoPagseguroValidator;
import deveficiente.mercadolivre.pedido.api.validators.PagamentoPaypalValidator;
import deveficiente.mercadolivre.pedido.dominio.CompraRepository;
import deveficiente.mercadolivre.pedido.dominio.PagamentoService;
import deveficiente.mercadolivre.pedido.dominio.comandos.TentativaPagamentoCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pagamento")
public class PagamentoController {

    private final PagamentoService pagamentoService;
    private final CompraRepository compraRepository;
    private final PagamentoPagseguroValidator pagseguroValidator;
    private final PagamentoPaypalValidator paypalValidator;

    @InitBinder("pagamentoPaypalRequest")
    public void initBinderPaypal(WebDataBinder binder) {
        binder.addValidators(paypalValidator);
    }

    @InitBinder("pagamentoPagseguroRequest")
    public void initBinderPagseguro(WebDataBinder binder) {
        binder.addValidators(pagseguroValidator);
    }

    @PostMapping(path = "/paypal")
    public ResponseEntity<Void> pagamentoPaypal(@Valid @RequestBody PagamentoPaypalRequest pagamento) {
        TentativaPagamentoCommand comando = pagamento.comando(compraRepository);
        pagamentoService.processar(comando);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/pagseguro")
    public ResponseEntity<Void> pagamentoPagseguro(@Valid @RequestBody PagamentoPagseguroRequest pagamento) {
        TentativaPagamentoCommand comando = pagamento.comando(compraRepository);
        pagamentoService.processar(comando);
        return ResponseEntity.noContent().build();
    }
}
