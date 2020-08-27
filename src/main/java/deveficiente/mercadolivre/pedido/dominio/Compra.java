package deveficiente.mercadolivre.pedido.dominio;

import deveficiente.mercadolivre.comum.exceptions.BusinessException;
import deveficiente.mercadolivre.produto.dominio.Produto;
import deveficiente.mercadolivre.usuario.dominio.Usuario;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@ToString
@EqualsAndHashCode(of = "id")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Compra {

    @Id
    @Getter
    @GeneratedValue
    private UUID id;
    @Getter
    @NotNull
    @Enumerated(EnumType.STRING)
    private GatewayPagamento gatewayPagamento;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id")
    private Produto produto;
    @Getter
    @Positive
    private int quantidade;
    @NotNull
    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusCompra status = StatusCompra.INICIADA;
    @OneToMany(mappedBy = "compra",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TentativaPagamento> tentativasPagamentos = new LinkedHashSet<>();

    public Compra(@NonNull GatewayPagamento gatewayPagamento, @NonNull Produto produto, int quantidade) {
        Assert.isTrue(quantidade > 0, "Quantidade deve ser > 0");
        this.gatewayPagamento = gatewayPagamento;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public String linkPagamento() {
        Assert.notNull(id, "Link de pagamento não pode ser gerado sem a compra existir");
        Assert.notNull(gatewayPagamento, "Link de pagamento não pode ser gerado sem o gateway ter sido informado");
        return gatewayPagamento.gerarLinkPagamento(id);
    }

    public void abaterEstoque() {
        Assert.notNull(produto, "Produto não deveria estar nulo");
        Assert.isTrue(quantidade > 0, "Quantidade deveria ser maior que 0");
        produto.abaterEstoqueEm(quantidade);
    }

    public String getEmailUsuario() {
        return usuario.getUsername();
    }

    public String getNomeProduto() {
        return produto.getNome();
    }

    public String getEmailVendedor() {
        return produto.getEmailVendedor();
    }

    public void addTentativaPagamento(String idGateway, StatusPagamento statusPagamento) {
        this.tentativasPagamentos.add(new TentativaPagamento(this, idGateway, statusPagamento));
    }

    public boolean utilizaGateway(GatewayPagamento gatewayPagamento) {
        return this.gatewayPagamento == gatewayPagamento;
    }

    public void setStatus(StatusCompra status) {
        if (isConcluida()) {
            throw BusinessException.of("compra.concluida.nao.pode.alterar.status");
        }
        this.status = status;
    }

    public boolean isConcluida() {
        return status == StatusCompra.CONCLUIDA;
    }

    public boolean possuiIdPagamento(@NonNull String idPagamento) {
        return tentativasPagamentos.stream()
                .anyMatch(pagamento -> idPagamento.equals(pagamento.getIdGateway()));
    }

    public BigDecimal getValorTotal() {
        return produto.getValor()
                .multiply(BigDecimal.valueOf(quantidade))
                .setScale(2, RoundingMode.HALF_UP);
    }
}
