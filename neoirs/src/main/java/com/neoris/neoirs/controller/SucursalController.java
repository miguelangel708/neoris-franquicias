package com.neoris.neoirs.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.neoris.neoirs.model.entity.Franquicia;
import com.neoris.neoirs.model.entity.Sucursal;
import com.neoris.neoirs.service.ISucursal;

@RestController
@RequestMapping("/api/v1/sucursal")
public class SucursalController {

    @Autowired
    private ISucursal sucursalService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody Sucursal sucursal) {
        
        Map<String, Object> response = new HashMap<>();

        try {
            Sucursal sucursalRespuesta = sucursalService.save(sucursal);
            return new ResponseEntity<>(sucursalRespuesta, HttpStatus.CREATED); // Retorna la sucursal creada con 201 Created

        } catch (DataIntegrityViolationException exDt) {
            response.put("error_message", exDt.getMessage());
            response.put("sucursal", null);
            response.put("mensaje", "No se pudo guardar la sucursal debido a que el id de franquicia no existe.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // 400 Bad Request en lugar de 404 Not Found
        }
    }

    @PutMapping("/updateName")
    public ResponseEntity<?> updateName(@RequestBody Sucursal sucursal){

        Map<String, Object> response = new HashMap<>();
        try{
            Sucursal sucursal_a_modificar = sucursalService.findById(sucursal.getId());
            sucursal_a_modificar.setNombre(sucursal.getNombre());
            Sucursal sucursal_modificado = sucursalService.save(sucursal_a_modificar);
            return new ResponseEntity<>(sucursal_modificado, HttpStatus.CREATED);

        }
        catch(NullPointerException exDt){
            response.put("error_message", exDt.getMessage());
            response.put("product", null);
            response.put("mensaje", "no existe ningún producto con el id ingresado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping
    public Sucursal update(@RequestBody Sucursal sucursal){
        return sucursalService.save(sucursal);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        Sucursal sucursalDelete = sucursalService.findById(id);
        sucursalService.delete(sucursalDelete);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Sucursal showById(@PathVariable Integer id){
        return sucursalService.findById(id);
    }

    
}
