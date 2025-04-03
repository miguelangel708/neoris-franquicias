package com.neoris.neoirs.model.dao;
import com.neoris.neoirs.model.entity.Producto;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductoDao extends CrudRepository<Producto, Integer>{

    @Query("SELECT p FROM Producto p WHERE p.sucursal.id = :sucursalId ORDER BY p.stock DESC LIMIT 1")
    Producto findTopBySucursalOrderByStockDesc(Integer sucursalId);

}
