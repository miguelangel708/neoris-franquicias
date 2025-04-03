package com.neoris.neoirs.service;

import com.neoris.neoirs.model.entity.Producto;
import java.util.List;
import java.util.Optional;

public interface IProducto {

    Producto save(Producto producto);

    Producto findById(Integer id);

    void delete(Producto producto);

    List<Producto> findMaxStockProductBySucursalForFranquicia(String franquiciaId);

    Optional<Producto> findByIdAndSucursal(Integer id, Integer sucursalId);
        

}
