package deveficiente.mercadolivre.produto.dominio;

import deveficiente.mercadolivre.categoria.dominio.Categoria;
import deveficiente.mercadolivre.comum.exceptions.BusinessException;
import deveficiente.mercadolivre.usuario.dominio.Usuario;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static java.util.Objects.requireNonNull;

@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@EntityListeners(AuditingEntityListener.class)
public class Produto {

    @Id
    @Getter
    @GeneratedValue
    private UUID id;
    @Getter
    @NotEmpty
    private String nome;
    @Getter
    @NotNull
    private BigDecimal valor;
    @Getter
    @NotNull
    private int quantidadeDisponivel;
    @Getter
    @NotEmpty
    private String descricao;
    @Valid
    @NotEmpty
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "produto_caracteristica", joinColumns = @JoinColumn(name = "produto_id"))
    private Set<CaracteristicaProduto> caracteristicas = new LinkedHashSet<>();
    @Valid
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "produto_foto", joinColumns = @JoinColumn(name = "produto_id"))
    private Set<FotoProduto> fotos = new LinkedHashSet<>();
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
    @Getter
    @NotNull
    @CreatedDate
    private LocalDateTime dataCriacao;
    @NotNull
    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendedor_id")
    private Usuario vendedor;

    public static ProdutoBuilder builder() {
        return new ProdutoBuilder();
    }

    public void adicionarFoto(@NonNull FotoProduto fotoProduto) {
        this.fotos.add(fotoProduto);
    }

    public List<CaracteristicaProduto> getCaracteristicas() {
        return new ArrayList<>(caracteristicas);
    }

    public List<FotoProduto> getFotos() {
        return new ArrayList<>(fotos);
    }

    public String getNomeCategoria() {
        return categoria.getNome();
    }

    public String getEmailVendedor() {
        return vendedor.getUsername();
    }

    public void abaterEstoqueEm(int quantidade) {
        if (quantidadeDisponivel < quantidade) {
            throw BusinessException.of("produto.estoque.indisponivel");
        }
        this.quantidadeDisponivel -= quantidade;
    }

    public static final class ProdutoBuilder {
        private String nome;
        private BigDecimal valor;
        private int quantidadeDisponivel;
        private Set<CaracteristicaProduto> caracteristicas = new LinkedHashSet<>();
        private String descricao;
        private Categoria categoria;

        private ProdutoBuilder() {
        }

        public ProdutoBuilder nome(@NonNull String nome) {
            this.nome = nome;
            return this;
        }

        public ProdutoBuilder valor(@NonNull BigDecimal valor) {
            this.valor = valor;
            return this;
        }

        public ProdutoBuilder quantidadeDisponivel(int quantidadeDisponivel) {
            this.quantidadeDisponivel = quantidadeDisponivel;
            return this;
        }

        public ProdutoBuilder caracteristicas(@NonNull Set<CaracteristicaProduto> caracteristicas) {
            this.caracteristicas = new LinkedHashSet<>(caracteristicas);
            return this;
        }

        public ProdutoBuilder descricao(@NonNull String descricao) {
            this.descricao = descricao;
            return this;
        }

        public ProdutoBuilder categoria(@NonNull Categoria categoria) {
            this.categoria = categoria;
            return this;
        }

        public Produto build() {
            Produto produto = new Produto();
            produto.valor = requireNonNull(this.valor);
            produto.nome = requireNonNull(this.nome);
            produto.quantidadeDisponivel = this.quantidadeDisponivel;
            produto.caracteristicas = requireNonNull(this.caracteristicas);
            produto.descricao = requireNonNull(this.descricao);
            produto.categoria = requireNonNull(this.categoria);
            return produto;
        }
    }
}
