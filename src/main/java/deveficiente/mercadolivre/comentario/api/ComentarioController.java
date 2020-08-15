package deveficiente.mercadolivre.comentario.api;

import deveficiente.mercadolivre.comentario.dominio.Comentario;
import deveficiente.mercadolivre.comentario.dominio.ComentarioRepository;
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
@RequestMapping("/api/v1/comentarios")
public class ComentarioController {

    private final ProdutoRepository produtoRepository;
    private final ComentarioRepository comentarioRepository;

    @PostMapping
    public ResponseEntity<ComentarioDTO> inserir(@RequestBody @Valid NovoComentarioRequest novoComentario) {
        Comentario comentario = novoComentario.entidade(produtoRepository);
        comentarioRepository.save(comentario);
        return ResponseEntity.ok(ComentarioDTO.from(comentario));
    }
}
