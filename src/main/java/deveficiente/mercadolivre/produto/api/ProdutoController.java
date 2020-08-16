package deveficiente.mercadolivre.produto.api;

import deveficiente.mercadolivre.categoria.dominio.CategoriaRepository;
import deveficiente.mercadolivre.comentario.api.ComentarioDTO;
import deveficiente.mercadolivre.comentario.dominio.ComentarioRepository;
import deveficiente.mercadolivre.pergunta.api.PerguntaDTO;
import deveficiente.mercadolivre.pergunta.dominio.PerguntaRepository;
import deveficiente.mercadolivre.produto.dominio.Produto;
import deveficiente.mercadolivre.produto.dominio.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Validated
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/produtos")
public class ProdutoController {

    private final CategoriaRepository categoriaRepository;
    private final ProdutoRepository produtoRepository;
    private final PerguntaRepository perguntaRepository;
    private final ComentarioRepository comentarioRepository;

    @PostMapping
    public ResponseEntity<ProdutoDTO> cadastrar(@RequestBody @Valid ProdutoRequest novoProduto) {
        Produto produto = novoProduto.modelo(categoriaRepository);
        produtoRepository.save(produto);
        return ResponseEntity.ok(ProdutoDTO.from(produto));
    }

    @GetMapping("/{idProduto}")
    public ResponseEntity<DetalheProdutoDTO> detalheProduto(@PathVariable UUID idProduto) {
        Produto produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new IllegalArgumentException("produto.nao.encontrado"));
        Set<PerguntaDTO> perguntas = perguntaRepository.findByProduto(produto)
                .stream()
                .map(PerguntaDTO::from)
                .collect(Collectors.toSet());
        Set<ComentarioDTO> comentarios = comentarioRepository.findByProduto(produto)
                .stream()
                .map(ComentarioDTO::from)
                .collect(Collectors.toSet());
        DetalheProdutoDTO detalheProduto = new DetalheProdutoDTO(ProdutoDTO.from(produto),
                perguntas,
                comentarios);
        return ResponseEntity.ok(detalheProduto);
    }
}
