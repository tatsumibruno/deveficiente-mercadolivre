package deveficiente.mercadolivre.produto.dominio;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ProdutoRepository extends CrudRepository<Produto, UUID> {
}
