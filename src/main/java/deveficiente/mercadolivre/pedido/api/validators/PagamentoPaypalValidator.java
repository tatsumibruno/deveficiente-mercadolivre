package deveficiente.mercadolivre.pedido.api.validators;

import deveficiente.mercadolivre.pedido.api.PagamentoPaypalRequest;
import deveficiente.mercadolivre.pedido.dominio.CompraRepository;
import deveficiente.mercadolivre.pedido.dominio.GatewayPagamento;
import org.springframework.stereotype.Component;

@Component
public class PagamentoPaypalValidator extends GatewayPagamentoValidator {

    public PagamentoPaypalValidator(CompraRepository compraRepository) {
        super(compraRepository);
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(PagamentoPaypalRequest.class);
    }

    @Override
    protected GatewayPagamento gatewayPagamento() {
        return GatewayPagamento.PAYPAL;
    }
}
