package deveficiente.mercadolivre.produto.api;

import deveficiente.mercadolivre.produto.dominio.CaracteristicaProduto;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CaracteristicaProdutoDTO {
    private String nome;
    private String descricao;

    public static CaracteristicaProdutoDTO from(CaracteristicaProduto caracteristicaProduto) {
        return new CaracteristicaProdutoDTO(caracteristicaProduto.getNome(), caracteristicaProduto.getDescricao());
    }
}
