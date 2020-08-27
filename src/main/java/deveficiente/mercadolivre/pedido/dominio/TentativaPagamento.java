package deveficiente.mercadolivre.pedido.dominio;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "compra_pagamento")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class TentativaPagamento {

    @Id
    @GeneratedValue
    private UUID id;
    @Getter
    @NotEmpty
    private String idGateway;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "compra_id")
    private Compra compra;
    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusPagamento status;
    @NotNull
    private final LocalDateTime dataHora = LocalDateTime.now();

    public TentativaPagamento(@NonNull Compra compra, @NonNull String idGateway, @NonNull StatusPagamento status) {
        this.idGateway = idGateway;
        this.compra = compra;
        this.status = status;
    }
}
