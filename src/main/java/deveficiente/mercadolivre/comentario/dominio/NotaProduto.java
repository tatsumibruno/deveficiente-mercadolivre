package deveficiente.mercadolivre.comentario.dominio;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Embeddable
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class NotaProduto {
    @Min(1)
    @Max(5)
    @Column(name = "nota_produto")
    private int valor;

    public static NotaProduto from(int nota) {
        Assert.isTrue(nota >= 1, "Nota deve ser >= 1");
        Assert.isTrue(nota <= 5, "Nota deve ser <= 5");
        return new NotaProduto(nota);
    }

    @Override
    public String toString() {
        return String.valueOf(valor);
    }
}
