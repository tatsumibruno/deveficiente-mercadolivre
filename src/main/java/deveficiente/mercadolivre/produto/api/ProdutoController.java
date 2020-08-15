package deveficiente.mercadolivre.produto.api;

import deveficiente.mercadolivre.categoria.dominio.CategoriaRepository;
import deveficiente.mercadolivre.produto.dominio.Produto;
import deveficiente.mercadolivre.produto.dominio.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/produtos")
public class ProdutoController {

    private final CategoriaRepository categoriaRepository;
    private final ProdutoRepository produtoRepository;

    @PostMapping
    public ResponseEntity<ProdutoDTO> cadastrar(@RequestBody @Valid ProdutoRequest novoProduto) {
        Produto produto = novoProduto.modelo(categoriaRepository);
        produtoRepository.save(produto);
        return ResponseEntity.ok(ProdutoDTO.from(produto));
    }
}
