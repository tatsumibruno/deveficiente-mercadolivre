package deveficiente.mercadolivre.pedido.api.validators;

import deveficiente.mercadolivre.pedido.api.TentativaPagamentoRequest;
import deveficiente.mercadolivre.pedido.dominio.Compra;
import deveficiente.mercadolivre.pedido.dominio.CompraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class PagamentoCompraSucessoValidator implements Validator {

    private final CompraRepository compraRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return TentativaPagamentoRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        TentativaPagamentoRequest pagamento = (TentativaPagamentoRequest) target;
        Compra compra = compraRepository.findById(pagamento.getIdCompra())
                .orElseThrow(() -> new IllegalArgumentException("compra.nao.encontrada"));
        if (compra.isConcluida()) {
            errors.rejectValue("idPagamento", "compra.ja.foi.concluida");
        }
        if (compra.possuiIdPagamento(pagamento.getIdPagamento())) {
            errors.rejectValue("idPagamento", "pagamento.ja.utilizado");
        }
    }
}
