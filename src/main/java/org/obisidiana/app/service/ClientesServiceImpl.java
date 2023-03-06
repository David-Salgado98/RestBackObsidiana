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
}
