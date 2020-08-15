package deveficiente.mercadolivre.produto.api;

import deveficiente.mercadolivre.produto.dominio.FotoProduto;
import deveficiente.mercadolivre.produto.dominio.FotoProdutoUpload;
import deveficiente.mercadolivre.produto.dominio.Produto;
import deveficiente.mercadolivre.produto.dominio.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/produtos")
public class ProdutoFotoController {

    private final ProdutoRepository produtoRepository;
    private final FotoProdutoUpload uploadFoto;

    @PostMapping("/foto")
    public ResponseEntity<String> gerarLinkUpload(@Valid @RequestBody @NotEmpty String titulo) {
        String novoLink = uploadFoto.gerarLinkNovaFoto(titulo);
        return ResponseEntity.ok(novoLink);
    }

    @Transactional
    @PostMapping("/{idProduto}/foto")
    public ResponseEntity<FotoProdutoDTO> adicionarFoto(@PathVariable UUID idProduto, @RequestBody @Valid FotoProdutoRequest novaFoto) {
        Produto produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new IllegalArgumentException("produto.nao.encontrado"));
        FotoProduto foto = novaFoto.entidade();
        produto.adicionarFoto(foto);
        return ResponseEntity.ok(FotoProdutoDTO.from(foto));
    }
}
