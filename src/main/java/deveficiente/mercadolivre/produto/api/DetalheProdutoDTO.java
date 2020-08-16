package deveficiente.mercadolivre.produto.api;

import deveficiente.mercadolivre.comentario.api.ComentarioDTO;
import deveficiente.mercadolivre.pergunta.api.PerguntaDTO;
import lombok.*;

import java.util.Set;
import java.util.TreeSet;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DetalheProdutoDTO {

    private ProdutoDTO produto;
    private Set<PerguntaDTO> perguntas = new TreeSet<>();
    private Set<ComentarioDTO> comentarios = new TreeSet<>();
}
