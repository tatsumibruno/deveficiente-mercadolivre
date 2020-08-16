package deveficiente.mercadolivre.pergunta.dominio;

import deveficiente.mercadolivre.produto.dominio.Produto;
import deveficiente.mercadolivre.usuario.dominio.Usuario;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Pergunta {

    @Id
    @Getter
    @GeneratedValue
    private UUID id;
    @Getter
    @NotEmpty
    private String titulo;
    @Getter
    @NotEmpty
    private String descricao;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id")
    private Produto produto;
    @NotNull
    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @Getter
    @NotNull
    @CreatedDate
    private LocalDateTime criadaEm;

    public Pergunta(@NonNull String titulo, @NonNull String descricao, @NonNull Produto produto) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.produto = produto;
    }

    public String getDescricaoUsuario() {
        return usuario.getUsername();
    }

    public String getEmailVendedor() {
        return produto.getEmailVendedor();
    }

    public String getEmailUsuario() {
        return usuario.getUsername();
    }
}
