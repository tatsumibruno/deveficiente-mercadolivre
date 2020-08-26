package deveficiente.mercadolivre.pedido.api;

import deveficiente.mercadolivre.pedido.dominio.Compra;
import deveficiente.mercadolivre.pedido.dominio.PagamentoService;
import deveficiente.mercadolivre.pedido.dominio.comandos.NovaCompraCommand;
import deveficiente.mercadolivre.pedido.dominio.NovoPedidoService;
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
    private final NovoPedidoService novoPedidoService;

    @PostMapping
    public ResponseEntity<UUID> novoPedido(@Valid @RequestBody NovoPedidoRequest request) {
        NovaCompraCommand novaCompra = request.comando(produtoRepository);
        Compra compra = novoPedidoService.realizar(novaCompra);
        return ResponseEntity.status(HttpStatus.FOUND)
                .header(HttpHeaders.LOCATION, compra.linkPagamento())
                .body(compra.getId());
    }
}
