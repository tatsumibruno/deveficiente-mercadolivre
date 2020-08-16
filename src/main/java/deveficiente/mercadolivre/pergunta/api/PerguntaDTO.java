package deveficiente.mercadolivre.pergunta.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import deveficiente.mercadolivre.pergunta.dominio.Pergunta;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PerguntaDTO {
    private UUID id;
    private String titulo;
    private String descricao;
    private String criadaPor;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime criadaEm;

    public static PerguntaDTO from(Pergunta pergunta) {
        return new PerguntaDTO(pergunta.getId(),
                pergunta.getTitulo(),
                pergunta.getDescricao(),
                pergunta.getDescricaoUsuario(),
                pergunta.getCriadaEm());
    }
}
