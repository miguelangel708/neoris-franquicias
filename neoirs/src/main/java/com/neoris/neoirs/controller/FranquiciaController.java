package com.neoris.neoirs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @ResponseStatus(HttpStatus.CREATED)
    public Franquicia create(@RequestBody Franquicia franquicia){
        System.out.println("Guardando franquicia: " + franquicia);
        return franquiciaService.save(franquicia);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Franquicia update(@RequestBody Franquicia franquicia){
        return franquiciaService.save(franquicia);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        Franquicia franquiciaDelete = franquiciaService.findById(id);
        franquiciaService.delete(franquiciaDelete);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Franquicia showById(@PathVariable Integer id){
        return franquiciaService.findById(id);
    }

    @PutMapping("/updateName")
    public ResponseEntity<?> updateName(@RequestBody Franquicia franquicia){

        Map<String, Object> response = new HashMap<>();
        try{
            Franquicia franquicia_a_modificar = franquiciaService.findById(franquicia.getId());
            franquicia_a_modificar.setNombre(franquicia.getNombre());
            Franquicia franquicia_modificado = franquiciaService.save(franquicia_a_modificar);
            return new ResponseEntity<>(franquicia_modificado, HttpStatus.CREATED);

        }
        catch(NullPointerException exDt){
            response.put("error_message", exDt.getMessage());
            response.put("product", null);
            response.put("mensaje", "no existe ningún producto con el id ingresado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        }
    }

}
