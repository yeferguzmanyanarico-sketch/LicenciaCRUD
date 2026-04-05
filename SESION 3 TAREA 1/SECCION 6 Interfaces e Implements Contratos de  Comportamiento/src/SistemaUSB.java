//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class SistemaUSB {
    public static void main(String[] args) {
        Computadora pc = new Computadora();

        pc.agregarDispositivo(new Teclado());
        pc.agregarDispositivo(new Mouse());
        pc.agregarDispositivo(new Impresora());
        pc.agregarDispositivo(new MemoriaUSB());

        pc.mostrarEstadoDispositivos();

        pc.desconectarTodos();
        pc.mostrarEstadoDispositivos();
    }
}