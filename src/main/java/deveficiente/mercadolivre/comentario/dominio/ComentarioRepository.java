package deveficiente.mercadolivre.comentario.dominio;

import deveficiente.mercadolivre.produto.dominio.Produto;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;
import java.util.UUID;

public interface ComentarioRepository extends CrudRepository<Comentario, UUID> {
    Set<Comentario> findByProduto(Produto produto);
}
