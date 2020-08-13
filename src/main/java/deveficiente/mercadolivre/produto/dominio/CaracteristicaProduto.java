package deveficiente.mercadolivre.produto.dominio;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@ToString
@Embeddable
@AllArgsConstructor
@EqualsAndHashCode(of = "label")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class CaracteristicaProduto implements Serializable {
    private String label;
    private String valor;
}
