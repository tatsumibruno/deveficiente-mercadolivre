package deveficiente.mercadolivre.comentario.dominio;

import deveficiente.mercadolivre.produto.dominio.Produto;
import deveficiente.mercadolivre.usuario.dominio.Usuario;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Comentario {

    @Id
    @Getter
    @GeneratedValue
    private UUID id;
    @Getter
    @Valid
    @NotNull
    @Embedded
    private NotaProduto nota;
    @Getter
    @NotEmpty
    private String titulo;
    @Getter
    @NotEmpty
    @Size(max = 500)
    private String descricao;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id")
    private Produto produto;
    @NotNull
    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario criadoPor;

    public Comentario(@NonNull NotaProduto nota, @NonNull String titulo, @NonNull String descricao, @NonNull Produto produto) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.produto = produto;
    }

    public String getDescricaoProduto() {
        return produto.getDescricao();
    }

    public String getDescricaoUsuario() {
        return criadoPor.getUsername();
    }
}
