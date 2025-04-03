package com.neoris.neoirs.model.dao;
import com.neoris.neoirs.model.entity.Producto;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProductoDao extends CrudRepository<Producto, Integer>{

    @Query("SELECT p FROM Producto p WHERE p.sucursal.id IN " +
       "(SELECT s.id FROM Sucursal s WHERE s.franquicia.id = :franquiciaId) " +
       "AND p.stock = (SELECT MAX(p2.stock) FROM Producto p2 WHERE p2.sucursal.id = p.sucursal.id)")
    List<Producto> findMaxStockProductBySucursalForFranquicia(@Param("franquiciaId") String franquiciaId);


    @Query("SELECT p FROM Producto p WHERE p.id = :id AND p.sucursal.id = :sucursalId")
    Optional<Producto> findByIdAndSucursalId(@Param("id") Integer id, @Param("sucursalId") Integer sucursalId);

}
