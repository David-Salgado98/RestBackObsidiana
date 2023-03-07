package org.obisidiana.app.service;

import org.obisidiana.app.entity.Clientes;
import org.obisidiana.app.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClientesServiceImpl implements ClientesService{

    @Autowired
    ClientesRepository clientesRepository;

    @Override
    public List<Clientes> findAllClientes() {
        return clientesRepository.findAll();
    }

    @Override
    public Clientes findByEmail(String email) {
        return clientesRepository.findByEmail(email).orElse(null);
    }

    @Override
    public void guardarCliente(Clientes clientes) {
        clientesRepository.save(clientes);
    }
}
