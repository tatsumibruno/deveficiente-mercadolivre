package deveficiente.mercadolivre.pergunta.dominio;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PerguntaRepository extends CrudRepository<Pergunta, UUID> {
}
