package deveficiente.mercadolivre.pergunta.dominio;

import deveficiente.mercadolivre.produto.dominio.Produto;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;
import java.util.UUID;

public interface PerguntaRepository extends CrudRepository<Pergunta, UUID> {
    Set<Pergunta> findByProduto(Produto produto);
}
