package deveficiente.mercadolivre.pedido.dominio;

import deveficiente.mercadolivre.comum.infra.mail.MailService;
import deveficiente.mercadolivre.produto.dominio.Produto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
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
    private final MailService mailService;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Compra realizar(@Valid NovaCompra novaCompra) {
        Produto produto = novaCompra.getProduto();
        Compra compra = new Compra(novaCompra.getGatewayPagamento(),
                produto,
                novaCompra.getQuantidade());
        compra.abaterEstoque();
        compraRepository.save(compra);
        notificar(compra);
        return compra;
    }

    private void notificar(Compra compra) {
        String corpoEmail = """
                Olá,
                O cliente %s realizou a compra de %s unidades do Produto %s.
                Para mais detalhes sobre a compra acesse: mercadolivre.com.br/pedidos/%s
                """
                .formatted(compra.getEmailUsuario(),
                        compra.getQuantidade(),
                        compra.getNomeProduto(),
                        compra.getId().toString());
        mailService.enviar(compra.getEmailVendedor(), "Nova compra", corpoEmail);
    }
}
