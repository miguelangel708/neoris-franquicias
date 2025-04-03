package com.neoris.neoirs.service;

import com.neoris.neoirs.model.entity.Sucursal;

public interface ISucursal {

    Sucursal save(Sucursal sucursal);
    Sucursal findById(Integer id);
    void delete(Sucursal sucursal);
    
}
