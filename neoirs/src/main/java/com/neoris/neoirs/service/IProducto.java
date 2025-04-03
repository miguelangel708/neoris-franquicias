package com.neoris.neoirs.service;

import com.neoris.neoirs.model.entity.Producto;
import java.util.List;

public interface IProducto {

    Producto save(Producto producto);

    Producto findById(Integer id);

    void delete(Producto producto);


}
