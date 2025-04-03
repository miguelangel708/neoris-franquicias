package com.neoris.neoirs.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.neoris.neoirs.model.entity.Producto;
import com.neoris.neoirs.service.IProducto;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/v1/producto")
public class ProductoController {

    @Autowired
    private IProducto productoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody Producto producto){

        Map<String, Object> response = new HashMap<>();

        try{
            Producto productoRespuesta = productoService.save(producto);
            return new ResponseEntity<>(productoRespuesta, HttpStatus.CREATED);

        }catch (DataIntegrityViolationException exDt){
            response.put("error_message", exDt.getMessage());
            response.put("product", null);
            response.put("mensaje", "no existe una sucursal con el sucursal_id ingresado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        }
    }
    
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping
    public Producto update(@RequestBody Producto producto){
        return productoService.save(producto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        Producto productoDelete = productoService.findById(id);
        productoService.delete(productoDelete);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Producto showById(@PathVariable Integer id){
        return productoService.findById(id);
    }
}
