package deveficiente.mercadolivre.pergunta.api;

import deveficiente.mercadolivre.comum.infra.mail.MailService;
import deveficiente.mercadolivre.pergunta.dominio.Pergunta;
import deveficiente.mercadolivre.pergunta.dominio.PerguntaRepository;
import deveficiente.mercadolivre.produto.dominio.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/perguntas")
public class PerguntaController {

    private final PerguntaRepository perguntaRepository;
    private final ProdutoRepository produtoRepository;
    private final MailService mailService;

    @PostMapping
    public ResponseEntity<PerguntaDTO> enviar(@RequestBody @Valid NovaPerguntaRequest novaPergunta) {
        Pergunta pergunta = novaPergunta.dominio(produtoRepository);
        perguntaRepository.save(pergunta);
        notificarVendedor(pergunta);
        return ResponseEntity.ok(PerguntaDTO.from(pergunta));
    }

    private void notificarVendedor(Pergunta pergunta) {
        String corpoEmail = new StringBuilder()
                .append("O usuário ")
                .append(pergunta.getEmailUsuario())
                .append(" fez uma nova pergunta.\n\n")
                .append("\"").append(pergunta.getDescricao()).append("\"")
                .toString();
        mailService.enviar(pergunta.getEmailVendedor(), "Nova Pergunta", corpoEmail);
    }
}
