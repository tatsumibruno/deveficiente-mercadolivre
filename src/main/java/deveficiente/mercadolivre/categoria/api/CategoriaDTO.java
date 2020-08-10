package deveficiente.mercadolivre.categoria.api;

import deveficiente.mercadolivre.categoria.dominio.Categoria;
import lombok.*;

import java.util.UUID;

@Getter
@ToString(of = {"id", "nome"})
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoriaDTO {
    private UUID id;
    private String nome;
    private String nomeCategoriaPai;

    public static CategoriaDTO from(Categoria categoria) {
        return new CategoriaDTO(categoria.getId(), categoria.getNome(), categoria.getNomeCategoriaPai());
    }
}
