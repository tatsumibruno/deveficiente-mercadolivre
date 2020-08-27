package deveficiente.mercadolivre.pedido.dominio;

import deveficiente.mercadolivre.comum.infra.mail.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EnvioEmailCompraConcluida implements ProcessoPosPagamento {

    private final MailService mailService;

    @Override
    public boolean deveProcessar(Compra compra) {
        return compra.isConcluida();
    }

    @Override
    public void processar(Compra compra) {
        mailService.enviar(compra.getEmailUsuario(),
                "Compra finalizada",
                """
                        Parabéns, seu pedido foi finalizado com sucesso.
                        Detalhes:
                        - Vendedor: %s
                        - Produto: %s
                        - Valor total: %s
                        - Meio de pagamento: %s
                        """
                        .formatted(compra.getEmailVendedor(),
                                compra.getNomeProduto(),
                                compra.getValorTotal(),
                                compra.getGatewayPagamento()));
    }
}
