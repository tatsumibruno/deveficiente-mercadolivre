package deveficiente.mercadolivre.comentario.dominio;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ComentarioRepository extends CrudRepository<Comentario, UUID> {
}
