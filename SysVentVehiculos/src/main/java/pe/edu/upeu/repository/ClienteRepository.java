package pe.edu.upeu.repository;

import pe.edu.upeu.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteRepository {
    List<Cliente> clientes = new ArrayList<>();

    public void  agregarCliente(Cliente c){
        clientes.add(c);
    }
    // R = Report
    public  List<Cliente> listarClientes(){
        return clientes;
    }
    //U=update
    public void actualizarCliente(Cliente c, int index){
        clientes.set(index, c);

    }
    // D = delate
    public  void  eliminarCliente(int index){
        clientes.remove(index);

    }
    public void eliminarTodo(){
        clientes.clear();

    }
    public void datosPredeterminados(){
        clientes.add(new Cliente("01","juaan rodirges quispe", "925588242","juan@gamil.com"));
        clientes.add(new Cliente("02","pedro huerffano sjsjjsjs"));
    }

    public int getClientes(){
        return clientes.size();
    }

}
