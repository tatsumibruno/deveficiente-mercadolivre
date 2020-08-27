package deveficiente.mercadolivre.pedido.dominio;

public interface ProcessoPosPagamento {

    boolean deveProcessar(Compra compra);

    void processar(Compra compra);
}
