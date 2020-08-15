package deveficiente.mercadolivre.produto.dominio;

import lombok.*;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@ToString
@Embeddable
@EqualsAndHashCode(of = "nome")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class CaracteristicaProduto implements Serializable {
    @NotEmpty
    private String nome;
    @NotEmpty
    private String descricao;

    public CaracteristicaProduto(@NonNull String nome, @NonNull String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }
}
