package org.obisidiana.app.controller;

import jakarta.validation.Valid;
import org.obisidiana.app.entity.Material;
import org.obisidiana.app.entity.Product;
import org.obisidiana.app.paso.Filter;
import org.obisidiana.app.service.MaterialService;
import org.obisidiana.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@CrossOrigin(origins="*")
//@RequestMapping("/pages")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private MaterialService materialService;


    @RequestMapping(value = "/index" ,method = RequestMethod.GET)
    public String index(){
        return "/index";
    }

    @RequestMapping(value = "pages/aboutus" ,method = RequestMethod.GET)
    public String aboutus(){
        return "pages/aboutus";
    }

    @GetMapping("pages/detalleProducto")
    public ResponseEntity<Product> procesar(@RequestParam Long id)
    {
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @RequestMapping(value = "pages/catalogo" ,method = RequestMethod.GET)
    public String catalogo(){
        return "pages/catalogo";
    }

    @RequestMapping(value = "pages/contacto" ,method = RequestMethod.GET)
    public String contacto(){
        return "pages/contacto";
    }

    @RequestMapping(value = "pages/findYourRock" ,method = RequestMethod.GET)
    public String yourRock(){
        return "pages/findYourRock";
    }

    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @RequestMapping(value = "pages/filtrar" ,method = RequestMethod.GET)
    public ResponseEntity<List<Product>> filtrar(){
        Filter filter = new Filter();
        List<Product> productos = productService.listAllProduct();

        if(productos.isEmpty()){
            return ResponseEntity.noContent().build();
        }


        return ResponseEntity.ok(productos);
    }

    @PostMapping("pages/filtrar")
    public ResponseEntity<List<Product>> procesar(@RequestBody Filter filter, BindingResult result)
    {
        List<Product> productos;
        if(filter.getMaterialesId().isEmpty() && filter.getTiposId().isEmpty()){
            productos = productService.listAllProduct();
        }else{
            if(!(filter.getMaterialesId().isEmpty()) && !(filter.getTiposId().isEmpty())){
                List<String> tipos = filter.getTiposId();
                List<Long> largosT= tipos.stream().map(a->Long.valueOf(a)).collect(Collectors.toList());
                List<String> mates = filter.getMaterialesId();
                List<Long> largosM = mates.stream().map(a->Long.valueOf(a)).collect(Collectors.toList());

                productos = productService.findByTiposAndMateriales(largosM,largosT);
            }

            else if(filter.getMaterialesId().isEmpty()){
                System.out.println(filter.getTiposId().get(0));
                List<String> tipos = filter.getTiposId();
               List<Long> largos = tipos.stream().map(Long::valueOf).collect(Collectors.toList());
               //largos.forEach(System.out::println);

               /* Long id = Long.valueOf(filter.getMaterialesId().get(0));
                Material material = materialService.getMaterial(id);
                List<Long> matIds = new ArrayList<>();
                matIds.add(1L);
                matIds.add(2L);*/
                System.out.println(largos);
                productos = productService.findByTipos(largos);
            }else{

                System.out.println("materiales"+filter.getMaterialesId().get(0));
                List<String> mates = filter.getMaterialesId();
                List<Long> largos = mates.stream().map(a->Long.valueOf(a)).collect(Collectors.toList());
               // largos.forEach(System.out::println);
                System.out.println("materiales"+largos);
                productos = productService.findByMateriales(largos);
            }


        }








        return ResponseEntity.ok(productos);
    }

   /* @GetMapping("pages/ver")
    public String ver(@ModelAttribute("filter")Filter filter, Model model, SessionStatus status) {


        System.out.println(filter.getValores().get(0));
        List<Product> productos = productService.listAllProduct();
        model.addAttribute("productos",productos);
        model.addAttribute("filter",filter);
        status.setComplete();// completa la session y elimina el usuario tambien en la base de datos
        return "pages/filtrar";
    }*/

    @RequestMapping(value = "pages/login" ,method = RequestMethod.GET)
    public String login(){
        return "pages/login";
    }

    @RequestMapping(value = "pages/tuPiedra" ,method = RequestMethod.GET)
    public String tuPiedra(){
        return "pages/tuPiedra";
    }
}
