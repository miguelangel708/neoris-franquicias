package com.neoris.neoirs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.neoris.neoirs.model.entity.Franquicia;
import com.neoris.neoirs.model.entity.Producto;
import com.neoris.neoirs.service.IFranquicia;
import com.neoris.neoirs.service.IProducto;

@RestController
@RequestMapping("/api/v1/franquicia")
public class FranquiciaController {

    @Autowired
    private IFranquicia franquiciaService;

    @PostMapping
    public Franquicia create(@RequestBody Franquicia franquicia){
        System.out.println("Guardando franquicia: " + franquicia);
        return franquiciaService.save(franquicia);
    }

    @PutMapping
    public Franquicia update(@RequestBody Franquicia franquicia){
        return franquiciaService.save(franquicia);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        Franquicia franquiciaDelete = franquiciaService.findById(id);
        franquiciaService.delete(franquiciaDelete);
    }

    @GetMapping("/{id}")
    public Franquicia showById(@PathVariable Integer id){
        return franquiciaService.findById(id);
    }

    @Autowired
    private IProducto productoService;

    @GetMapping("franquicia/{id}/top-products")
    public List<Producto> getTopProductsByFranquicia(@PathVariable Integer id) {
        return productoService.findTopProductsByFranquicia(id);
    }
}
