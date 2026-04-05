//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class SistemasPedidos {
    public static void main(String[] args) {
        Pedido pedido = new Pedido("Ana");

        pedido.mostrarInfo();
        pedido.cambiarEstado(EstadoPedido.CONFIRMADO);
        pedido.cambiarEstado(EstadoPedido.EN_PREPARACION);
        pedido.cambiarEstado(EstadoPedido.ENVIADO);
        pedido.cambiarEstado(EstadoPedido.ENTREGADO);
        pedido.cambiarEstado(EstadoPedido.CANCELADO); // inválido
    }
}