//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class SistemaInventario {
    public static void main(String[] args) {
        Inventario inventario = new Inventario();

        inventario.agregarProducto(new Producto("Laptop", 5));
        inventario.agregarProducto(new Producto("Mouse", 0));
        inventario.agregarProducto(new Producto("Teclado", 3));
        inventario.agregarProducto(new Producto("Monitor", 0));
        inventario.agregarProducto(new Producto("Impresora", 2));

        // Uso del iterador personalizado con for-each
        System.out.println("Productos disponibles:");
        for (Producto p : inventario) {
            System.out.println(p);
        }
    }
}