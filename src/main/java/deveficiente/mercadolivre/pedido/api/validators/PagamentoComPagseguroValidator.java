package deveficiente.mercadolivre.pedido.api.validators;

import deveficiente.mercadolivre.pedido.api.PagamentoPagseguroRequest;
import deveficiente.mercadolivre.pedido.dominio.CompraRepository;
import deveficiente.mercadolivre.pedido.dominio.GatewayPagamento;
import org.springframework.stereotype.Component;

@Component
public class PagamentoComPagseguroValidator extends GatewayPagamentoValidator {

    public PagamentoComPagseguroValidator(CompraRepository compraRepository) {
        super(compraRepository);
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(PagamentoPagseguroRequest.class);
    }

    @Override
    protected GatewayPagamento gatewayPagamento() {
        return GatewayPagamento.PAGSEGURO;
    }
}
