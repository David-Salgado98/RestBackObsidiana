package org.obisidiana.app.controller;

import org.obisidiana.app.entity.Clientes;
import org.obisidiana.app.service.ClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("cli")
@CrossOrigin(origins="*")
public class ClientesController {
    @Autowired
    private ClientesService clientesService;

    @RequestMapping(value = "pages/clientes" ,method = RequestMethod.GET)
    public List<Clientes> allClientes(){
        return clientesService.findAllClientes();
    }
}
