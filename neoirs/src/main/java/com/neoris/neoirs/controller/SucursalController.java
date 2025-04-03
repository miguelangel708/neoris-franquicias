package com.neoris.neoirs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.neoris.neoirs.model.entity.Sucursal;
import com.neoris.neoirs.service.ISucursal;

@RestController
@RequestMapping("/api/v1/sucursal")
public class SucursalController {

    @Autowired
    private ISucursal sucursalService;

    @PostMapping
    public Sucursal create(@RequestBody Sucursal sucursal){
        System.out.println("Guardando sucursal: " + sucursal);
        return sucursalService.save(sucursal);
    }

    @PutMapping
    public Sucursal update(@RequestBody Sucursal sucursal){
        return sucursalService.save(sucursal);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        Sucursal sucursalDelete = sucursalService.findById(id);
        sucursalService.delete(sucursalDelete);
    }

    @GetMapping("/{id}")
    public Sucursal showById(@PathVariable Integer id){
        return sucursalService.findById(id);
    }
}
