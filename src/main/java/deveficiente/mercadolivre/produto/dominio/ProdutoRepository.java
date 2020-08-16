package deveficiente.mercadolivre.produto.dominio;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProdutoRepository extends CrudRepository<Produto, UUID> {

    @Override
    @Query("select produto from Produto produto join fetch produto.vendedor join fetch produto.caracteristicas join fetch produto.categoria left join fetch produto.fotos where produto.id = :id")
    Optional<Produto> findById(UUID id);
}
