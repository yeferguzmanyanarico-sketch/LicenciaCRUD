import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ListaCompras {
    private ArrayList<String> productos;

    //constructor
    public ListaCompras(){
        productos = new ArrayList<>();

    }
    //agregar productos con validacion de duplicados
    public void agregarProductos (String producto){
        if(!productos.contains(producto)){
            productos.add(producto);
            System.out.println(producto+" AGREGADO A LA LISTA DE PRUDUCCTOS.");
        } else {
            System.out.println(producto+" YA ESTA EN LA LISTA DE PRODUCCTOS.");
        }
    }

    //eliminar producto
    public void eliminarProducto(String producto){
        if(productos.remove(producto)){
            System.out.println(producto+"ELIMINADO DE LA LISTA DE PRODUCCTOS.");
        } else {
            System.out.println(producto+"NO SE ENCONTRO EN LA LISTA DE PRODUCCTOS. ");

        }
    }
    //BUSCAR PRODUCCTO
    public boolean buscarProductos(String producto){
        return productos.contains(producto);
    }
    //CONTAR PRODUCTO
    public int contarProducto(){
        return productos.size();

    }
    //ORDENAR ALFABETICAMENTE
    public void ordenarAlfabeticamente(){
        Collections.sort(productos);
        System.out.println("LISTA ORDENADO ALFABETICAMENTE." + productos);
    }
    //Mostrar lista completa
    public void mostrarLista(){
        System.out.println("Lista de comprar: " + productos);
    }
    //metodo de main para probar
    public static void main(String[] args) {
        ListaCompras lista = new ListaCompras();

        lista.agregarProductos("LECHE:");
        lista.agregarProductos("PAN:");
        lista.agregarProductos("LECHE:"); // DUPLICADO

        lista.mostrarLista();

        lista.eliminarProducto("PAN:");
        lista.mostrarLista();

        System.out.println("¿esta 'leche' en la lista?" + lista.buscarProductos("leche"));
        System.out.println("total de productos:"+lista.contarProducto());

        lista.agregarProductos("Arroz ");
        lista.agregarProductos("Aceite ");
        lista.ordenarAlfabeticamente();
        lista.mostrarLista();
    }
}
