package deveficiente.mercadolivre.pedido.api.validators;

import deveficiente.mercadolivre.pedido.api.TentativaPagamentoRequest;
import deveficiente.mercadolivre.pedido.dominio.CompraRepository;
import deveficiente.mercadolivre.pedido.dominio.GatewayPagamento;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@RequiredArgsConstructor
public abstract class GatewayPagamentoValidator implements Validator {

    private final CompraRepository compraRepository;

    @Override
    public void validate(Object target, Errors errors) {
        TentativaPagamentoRequest pagamento = (TentativaPagamentoRequest) target;
        compraRepository.findById(pagamento.getIdCompra())
                .ifPresentOrElse(compra -> {
                    if (!compra.utilizaGateway(gatewayPagamento())) {
                        errors.rejectValue("idCompra", "gateway.pagamento.invalido");
                    }
                }, () -> errors.rejectValue("idCompra", "compra.nao.encontrada"));
    }

    protected abstract GatewayPagamento gatewayPagamento();
}
