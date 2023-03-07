package org.obisidiana.app.controller;

import org.obisidiana.app.entity.Clientes;
import org.obisidiana.app.entity.Product;
import org.obisidiana.app.paso.Filter;
import org.obisidiana.app.service.ClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("cli")
@CrossOrigin(origins="*")
public class ClientesController {
    @Autowired
    private ClientesService clientesService;

    @RequestMapping(value = "pages/clientes" ,method = RequestMethod.GET)
    public List<Clientes> allClientes(){
        return clientesService.findAllClientes();
    }

    @RequestMapping(value = "pages/nuevocliente" ,method = RequestMethod.POST)
    public ResponseEntity<?> guardar(@RequestBody Clientes clientes, BindingResult result) {
        Clientes busca;
        if(clientes != null){
             busca = clientesService.findByEmail(clientes.getEmail());
            if(busca == null){
                clientesService.guardarCliente(clientes);
                return ResponseEntity.ok("Usuario guardado");
            }else{
                return  new ResponseEntity<String>("El email introducido ya esta en uso",HttpStatus.BAD_REQUEST );
            }


        }
        return  new ResponseEntity<String>("Error en el envio de datos",HttpStatus.BAD_REQUEST );


    }


}
