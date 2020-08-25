package deveficiente.mercadolivre.pedido.dominio;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class TentativaPagamento {

    @Id
    @GeneratedValue
    private UUID id;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "compra_id")
    private Compra compra;
    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusPagamento status;
    @NotNull
    private final LocalDateTime dataHora = LocalDateTime.now();

    public TentativaPagamento(@NonNull Compra compra, @NonNull StatusPagamento status) {
        this.compra = compra;
        this.status = status;
    }
}
