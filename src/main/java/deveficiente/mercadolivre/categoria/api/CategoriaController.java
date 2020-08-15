package deveficiente.mercadolivre.categoria.api;

import deveficiente.mercadolivre.categoria.dominio.Categoria;
import deveficiente.mercadolivre.categoria.dominio.CategoriaRepository;
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
@RequestMapping("/api/v1/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    @PostMapping
    public ResponseEntity<CategoriaDTO> cadastrar(@RequestBody @Valid NovaCategoriaRequest novaCategoria) {
        Categoria categoria = novaCategoria.modelo(categoriaRepository);
        categoriaRepository.save(categoria);
        return ResponseEntity.ok(CategoriaDTO.from(categoria));
    }
}
