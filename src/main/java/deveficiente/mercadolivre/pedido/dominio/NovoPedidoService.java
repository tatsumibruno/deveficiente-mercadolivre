package deveficiente.mercadolivre.pedido.dominio;

import deveficiente.mercadolivre.comum.infra.mail.MailService;
import deveficiente.mercadolivre.pedido.dominio.comandos.NovaCompraCommand;
import deveficiente.mercadolivre.produto.dominio.Produto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
@RequiredArgsConstructor
public class NovoPedidoService {

    private final CompraRepository compraRepository;
    private final MailService mailService;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Compra realizar(@Valid NovaCompraCommand novaCompra) {
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
        String corpoEmail = String.format("Olá,\n" +
                        "O cliente %s realizou a compra de %s unidades do Produto %s.\n" +
                        "Para mais detalhes sobre a compra acesse: mercadolivre.com.br/pedidos/%s",
                compra.getEmailUsuario(),
                compra.getQuantidade(),
                compra.getNomeProduto(),
                compra.getId().toString());
        mailService.enviar(compra.getEmailVendedor(), "Nova compra", corpoEmail);
    }
}
