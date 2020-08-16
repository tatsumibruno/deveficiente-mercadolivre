package deveficiente.mercadolivre.comentario.api;

import deveficiente.mercadolivre.comentario.dominio.Comentario;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ComentarioDTO {
    private UUID id;
    private String nota;
    private String titulo;
    private String descricao;
    private String produto;
    private String criadoPor;

    public static ComentarioDTO from(Comentario comentario) {
        return new ComentarioDTO(comentario.getId(),
                comentario.getNota().toString(),
                comentario.getTitulo(),
                comentario.getDescricao(),
                comentario.getDescricaoProduto(),
                comentario.getDescricaoUsuario());
    }
}
