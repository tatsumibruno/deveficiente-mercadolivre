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
        String corpoEmail = String.format(new StringBuilder()
                        .append("Parabéns, seu pedido foi finalizado com sucesso.\n")
                        .append("Detalhes:\n")
                        .append("- Vendedor: %s\n")
                        .append("- Produto: %s\n")
                        .append("- Valor total: %s\n")
                        .append("- Meio de pagamento: %s")
                        .toString(), compra.getEmailVendedor(),
                compra.getNomeProduto(),
                compra.getValorTotal(),
                compra.getGatewayPagamento());
        mailService.enviar(compra.getEmailUsuario(), "Compra finalizada", corpoEmail);
    }
}
