import java.util.ArrayList;
import java.util.Iterator;

// Clase Inventario que implementa Iterable
class Inventario implements Iterable<Producto> {
    private ArrayList<Producto>productos = new ArrayList<>();

    public void agregarProducto(Producto p) {
        productos.add(p);
    }

    // Clase interna que implementa Iterator
    private class IteradorDisponible implements Iterator<Producto> {
        private int posicion = 0;

        @Override
        public boolean hasNext() {
            // Avanza hasta encontrar un producto con stock
            while (posicion < productos.size() &&
                    productos.get(posicion).getStock() <= 0) {
                posicion++;
            }
            return posicion < productos.size();
        }

        @Override
        public Producto next() {
            return productos.get(posicion++);
        }
    }

    @Override
    public Iterator<Producto> iterator() {
        return new IteradorDisponible();
    }
}
