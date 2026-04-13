package pe.edu.upeu.service;

import pe.edu.upeu.model.Cliente;

import java.util.List;

public interface ClienteServiceInter {
    void save(Cliente c);
    List<Cliente> findAll();
    void update (Cliente c, int index );
    void delete(int index);

}
