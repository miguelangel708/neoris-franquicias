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
    @PutMapping("/updateStock")
    public ResponseEntity<?> updateStock(@RequestBody Producto producto){

        Map<String, Object> response = new HashMap<>();
        try{
            Producto producto_a_modificar = productoService.findById(producto.getId());
            producto_a_modificar.setStock(producto.getStock());
            Producto producto_modificado = productoService.save(producto_a_modificar);
            return new ResponseEntity<>(producto_modificado, HttpStatus.CREATED);

        }
        catch(NullPointerException exDt){
            response.put("error_message", exDt.getMessage());
            response.put("product", null);
            response.put("mensaje", "no existe ningún producto con el id ingresado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        }
    }

    @PutMapping("/updateName")
    public ResponseEntity<?> updateName(@RequestBody Producto producto){

        Map<String, Object> response = new HashMap<>();
        try{
            Producto producto_a_modificar = productoService.findById(producto.getId());
            producto_a_modificar.setNombre(producto.getNombre());
            Producto producto_modificado = productoService.save(producto_a_modificar);
            return new ResponseEntity<>(producto_modificado, HttpStatus.CREATED);

        }
        catch(NullPointerException exDt){
            response.put("error_message", exDt.getMessage());
            response.put("product", null);
            response.put("mensaje", "no existe ningún producto con el id ingresado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody Producto producto){
        
        Map<String, Object> response = new HashMap<>();
        Producto productoDelete = productoService.findById(producto.getId());
       
        if ( productoDelete.getSucursal_id() == producto.getSucursal_id()){
            productoService.delete(productoDelete);
            return new ResponseEntity<>(productoDelete, HttpStatus.OK);

        }
        
        response.put("mensaje", "no existe un producto asociado al sucursal_id ingresado");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Producto showById(@PathVariable Integer id){
        return productoService.findById(id);
    }
}
