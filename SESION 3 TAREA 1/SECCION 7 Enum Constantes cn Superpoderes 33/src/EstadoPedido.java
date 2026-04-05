//Enum con estados de pedido
public enum EstadoPedido {
    PENDIENTE("Esperando confirmación", "#FFA500"),
    CONFIRMADO("Pedido confirmado", "#2196F3"),
    EN_PREPARACION("En preparación", "#9C27B0"),
    ENVIADO("En camino", "#4CAF50"),
    ENTREGADO("Entregado", "#008D4A"),
    CANCELADO("Cancelado", "#FF4436");

    private String descripcion;
    private String color;

    EstadoPedido(String descripcion, String color) {
        this.descripcion = descripcion;
        this.color = color;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getColor() {
        return color;
    }

    // Método para validar transiciones
    public boolean puedeTransicionarA(EstadoPedido nuevoEstado) {
        switch (this) {
            case PENDIENTE:
                return nuevoEstado == CONFIRMADO || nuevoEstado == CANCELADO;
            case CONFIRMADO:
                return nuevoEstado == EN_PREPARACION || nuevoEstado == CANCELADO;
            case EN_PREPARACION:
                return nuevoEstado == ENVIADO || nuevoEstado == CANCELADO;
            case ENVIADO:
                return nuevoEstado == ENTREGADO;
            case ENTREGADO:
                return false; // estado final
            case CANCELADO:
                return false; // estado final
            default:
                return false;
        }
    }
}
