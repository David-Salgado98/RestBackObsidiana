package org.obisidiana.app.service;

import org.obisidiana.app.entity.Clientes;

import java.util.List;

public interface ClientesService {
    public List<Clientes> findAllClientes();

    public  Clientes findByEmail(String email);

    public void guardarCliente(Clientes clientes);


}
