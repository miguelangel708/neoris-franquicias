package com.neoris.neoirs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

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
    public ResponseEntity<?> create(@RequestBody Producto producto) {

        Map<String, Object> response = new HashMap<>();

        // Validamos los datos esenciales
        if (producto.getStock() == null || 
            producto.getSucursal() == null || 
            producto.getSucursal().getId() == null || 
            producto.getNombre() == null || 
            producto.getNombre().trim().isEmpty()) {
            
            response.put("mensaje", "El producto debe incluir un nombre, un sucursal_id y un número de stock válido.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        // Verificamos si el producto ya existe
        Optional<Producto> productoEncontrado = productoService.findByIdAndSucursal(producto.getId(), producto.getSucursal().getId());

        if (productoEncontrado.isPresent()) {
            response.put("mensaje", "El producto con ID " + producto.getId() + " ya está registrado en la sucursal " + producto.getSucursal().getId());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        // IMPORTANTE: Asegurar que el ID sea nulo antes de guardar
        producto.setId(null);

        // Guardamos el nuevo producto
        Producto nuevoProducto = productoService.save(producto);
        response.put("mensaje", "Producto creado con éxito.");
        response.put("producto", nuevoProducto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    
    
    @ResponseStatus(HttpStatus.OK)  // Cambiado de CREATED (201) a OK (200), ya que estamos actualizando
    @PutMapping("/updateStock")
    public ResponseEntity<?> updateStock(@RequestBody Producto producto) {
        Map<String, Object> response = new HashMap<>();

        // Validamos que todos los datos necesarios estén presentes
        if (producto.getId() == null || producto.getStock() == null ||
            producto.getSucursal() == null || producto.getSucursal().getId() == null) {
            response.put("mensaje", "El producto debe incluir un ID, un sucursal_id y un número de stock válido.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Optional<Producto> productoEncontrado = productoService.findByIdAndSucursal(producto.getId(), producto.getSucursal().getId());

        if (productoEncontrado.isPresent()) {
            Producto productoActualizado = productoEncontrado.get();  // Obtenemos el producto de la base de datos
            productoActualizado.setStock(producto.getStock());  // Actualizamos el stock
            productoService.save(productoActualizado);  // Guardamos el producto actualizado
            response.put("mensaje", "Stock del producto actualizado con éxito.");
            return ResponseEntity.ok(response);
        } else {
            response.put("mensaje", "No se encontró un producto con el ID " + producto.getId() + " y Sucursal ID " + producto.getSucursal().getId());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }


    @PutMapping("/updateName")
    public ResponseEntity<?> updateName(@RequestBody Producto producto) {
        Map<String, Object> response = new HashMap<>();

        if (producto.getId() == null || producto.getNombre() == null
            || producto.getSucursal() == null || producto.getSucursal().getId() == null) {
            response.put("mensaje", "El producto debe incluir un ID, un sucursal_id y un nombre válido.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Optional<Producto> productoEncontrado = productoService.findByIdAndSucursal(producto.getId(), producto.getSucursal().getId());

        if (productoEncontrado.isPresent()) {
            Producto productoActualizado = productoEncontrado.get();  // Obtenemos el objeto existente
            productoActualizado.setNombre(producto.getNombre());  // Modificamos el nombre
            productoService.save(productoActualizado);  // Guardamos el producto actualizado
            response.put("mensaje", "Nombre del producto actualizado con éxito.");
            return ResponseEntity.ok(response);
        } else {
            response.put("mensaje", "No se encontró un producto con el ID " + producto.getId() + " y Sucursal ID " + producto.getSucursal().getId());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody Producto producto) {
        Map<String, Object> response = new HashMap<>();

        if (producto.getId() == null || producto.getSucursal() == null || producto.getSucursal().getId() == null) {
            response.put("mensaje", "El producto debe incluir un ID y un sucursal_id válido.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Optional<Producto> productoEncontrado = productoService.findByIdAndSucursal(producto.getId(), producto.getSucursal().getId());

        if (productoEncontrado.isPresent()) {
            productoService.delete(productoEncontrado.get());
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            response.put("mensaje", "No se encontró un producto con el ID " + producto.getId() + " y Sucursal ID " + producto.getSucursal().getId());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }


    @GetMapping("/{id}/sucursal/{sucursalId}")
    public ResponseEntity<?> getProductoByIdAndSucursal(@PathVariable Integer id, @PathVariable Integer sucursalId) {
        Optional<Producto> producto = productoService.findByIdAndSucursal(id, sucursalId);

        if (producto.isPresent()) {
            return ResponseEntity.ok(producto.get());
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "No se encontró un producto con el ID " + id + " y Sucursal ID " + sucursalId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }



    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Producto showById(@PathVariable Integer id){
        return productoService.findById(id);
    }

    @GetMapping("/max-stock/{franquiciaId}")
    public ResponseEntity<?> getMaxStockProductBySucursal(@PathVariable String franquiciaId) {
        
        List<Producto> productos = productoService.findMaxStockProductBySucursalForFranquicia(franquiciaId);
        
        if (productos.isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "No se encontraron productos para la franquicia indicada");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

}
