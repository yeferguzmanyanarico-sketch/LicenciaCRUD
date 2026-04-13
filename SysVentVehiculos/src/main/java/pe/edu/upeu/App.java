package pe.edu.upeu;

import pe.edu.upeu.enums.TipoVehiculo;
import pe.edu.upeu.model.Cliente;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {

        System.out.println(TipoVehiculo.SEDAN.toString());

        for (TipoVehiculo tv : TipoVehiculo.values()){
            System.out.println(tv.toString());

        }

        List<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente("02", "Javier","987456123","gsfdfa@gmail"));
        clientes.add(new Cliente("03", "jos","445554658","gsfdfa@gmail"));
        clientes.add(new Cliente("04", "cbscja","924546325","gsfdfa@gmail"));
        clientes.add(new Cliente("05", "Carlos","936325544","gsfdfa@gmail"));

        for (Cliente c:clientes){
            System.out.println(c.toString());
        }


        Cliente p = new Cliente("01", "YEFER YANARICO");

        System.out.println(p.toString());
    }



}
