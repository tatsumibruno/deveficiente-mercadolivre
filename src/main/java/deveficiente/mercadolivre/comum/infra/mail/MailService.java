package deveficiente.mercadolivre.comum.infra.mail;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.io.IOException;

@Slf4j
@Service
@Validated
public class MailService {

    @Value("${sendgrid.api-key}")
    private String apiKey;
    @Value("${sendgrid.from}")
    private String from;

    @Async
    public void enviar(@Valid @javax.validation.constraints.Email @NonNull String para,
                       @NonNull String assunto,
                       @NonNull String corpo) {
        Email from = new Email(this.from);
        Email to = new Email(para);
        Content content = new Content("text/plain", corpo);
        Mail mail = new Mail(from, assunto, to, content);

        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            log.info("Resposta do envio de e-mail.");
            log.info(String.format("Status: %s", response.getStatusCode()));
            log.info(String.format("Corpo: %s", response.getBody()));
            log.info(String.format("Headers: %s", response.getHeaders()));
        } catch (IOException ex) {
            log.error("Erro ao enviar e-mail.", ex);
        }
    }
}
