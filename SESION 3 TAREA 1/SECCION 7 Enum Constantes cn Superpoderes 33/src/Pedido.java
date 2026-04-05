// Clase Pedido que usa el enum
class Pedido {
    private String cliente;
    private EstadoPedido estado;

    public Pedido(String cliente) {
        this.cliente = cliente;
        this.estado = EstadoPedido.PENDIENTE;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void cambiarEstado(EstadoPedido nuevoEstado) {
        if (estado.puedeTransicionarA(nuevoEstado)) {
            estado = nuevoEstado;
            System.out.println("Estado cambiado a: " + estado.getDescripcion());
        } else {
            System.out.println("Transición inválida desde " + estado + " a " + nuevoEstado);
        }
    }

    public void mostrarInfo() {
        System.out.println("Pedido de " + cliente + " | Estado: " + estado.getDescripcion() + " | Color: " + estado.getColor());
    }
}