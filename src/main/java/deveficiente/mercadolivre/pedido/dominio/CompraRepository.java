package deveficiente.mercadolivre.pedido.dominio;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface CompraRepository extends JpaRepository<Compra, UUID> {
}
