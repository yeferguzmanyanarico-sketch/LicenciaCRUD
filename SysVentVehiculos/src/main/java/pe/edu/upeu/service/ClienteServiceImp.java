package pe.edu.upeu.service;

import pe.edu.upeu.model.Cliente;
import pe.edu.upeu.repository.ClienteRepository;

import java.util.List;

public class ClienteServiceImp implements ClienteServiceInter{

    ClienteRepository cr = new ClienteRepository();

    @Override
    public void save(Cliente c) {
        cr.agregarCliente(c);
    }

    @Override
    public List<Cliente> findAll() {
        return cr.listarClientes();
    }

    @Override
    public void update(Cliente c, int index) {
        cr.actualizarCliente(c,index);
    }

    @Override
    public void delete(int index) {
        cr.eliminarCliente(index);
    }
}
