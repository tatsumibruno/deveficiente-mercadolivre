package deveficiente.mercadolivre.produto.api;

import deveficiente.mercadolivre.produto.dominio.CaracteristicaProduto;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CaracteristicaProdutoRequest {
    private String nome;
    private String descricao;

    public CaracteristicaProduto modelo() {
        return new CaracteristicaProduto(nome, descricao);
    }
}
