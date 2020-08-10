package deveficiente.mercadolivre.categoria.api;

import deveficiente.mercadolivre.categoria.dominio.Categoria;
import deveficiente.mercadolivre.categoria.dominio.CategoriaRepository;
import deveficiente.mercadolivre.comum.api.validators.Unique;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.util.Optional;
import java.util.UUID;

@Getter
@ToString
public class NovaCategoriaRequest {

    @NotEmpty
    @Unique(targetClass = Categoria.class, field = "nome", message = "{categoria.ja.existe}")
    private String nome;
    private UUID idCategoriaPai;

    public Categoria entidade(CategoriaRepository categoriaRepository) {
        Categoria categoriaPai = Optional.ofNullable(idCategoriaPai)
                .map(idCategoria -> categoriaRepository.findById(idCategoria)
                        .orElseThrow(() -> new IllegalArgumentException("categoria.pai.nao.encontrada")))
                .orElse(null);
        return new Categoria(nome, categoriaPai);
    }
}
