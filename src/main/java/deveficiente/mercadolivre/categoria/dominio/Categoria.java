package deveficiente.mercadolivre.categoria.dominio;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Optional;
import java.util.UUID;

@Entity
@ToString(of = {"id", "nome"})
@EqualsAndHashCode(of = "nome")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Categoria {

    @Id
    @Getter
    @GeneratedValue
    private UUID id;
    @Getter
    @NotEmpty
    private String nome;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_pai_id")
    private Categoria categoriaPai;

    public Categoria(@NonNull String nome, Categoria categoriaPai) {
        this.nome = nome;
        this.categoriaPai = categoriaPai;
    }

    public String getNomeCategoriaPai() {
        return Optional.ofNullable(categoriaPai)
                .map(Categoria::getNome)
                .orElse(null);
    }
}
