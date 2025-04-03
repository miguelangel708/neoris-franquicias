package com.neoris.neoirs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.neoris.neoirs.model.entity.Producto;
import com.neoris.neoirs.service.IProducto;

@RestController
@RequestMapping("/api/v1/producto")
public class ProductoController {

    @Autowired
    private IProducto productoService;

    @PostMapping
    public Producto create(@RequestBody Producto producto){
        System.out.println("Guardando producto: " + producto);
        return productoService.save(producto);
    }

    @PutMapping
    public Producto update(@RequestBody Producto producto){
        return productoService.save(producto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        Producto productoDelete = productoService.findById(id);
        productoService.delete(productoDelete);
    }

    @GetMapping("/{id}")
    public Producto showById(@PathVariable Integer id){
        return productoService.findById(id);
    }
}
