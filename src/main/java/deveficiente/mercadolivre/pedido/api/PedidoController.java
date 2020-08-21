package deveficiente.mercadolivre.pedido.api;

import deveficiente.mercadolivre.pedido.dominio.Compra;
import deveficiente.mercadolivre.pedido.dominio.NovaCompra;
import deveficiente.mercadolivre.pedido.dominio.PedidoService;
import deveficiente.mercadolivre.produto.dominio.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pedidos")
public class PedidoController {

    private final ProdutoRepository produtoRepository;
    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<UUID> realizar(@Valid @RequestBody PedidoRequest request) {
        NovaCompra novaCompra = request.comando(produtoRepository);
        Compra compra = pedidoService.realizar(novaCompra);
        return ResponseEntity.status(HttpStatus.FOUND)
                .header(HttpHeaders.LOCATION, compra.linkPagamento())
                .body(compra.getId());
    }
}
