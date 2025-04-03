package com.neoris.neoirs.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neoris.neoirs.model.dao.ProductoDao;
import com.neoris.neoirs.model.dao.SucursalDao;

import com.neoris.neoirs.model.entity.Producto;
import com.neoris.neoirs.model.entity.Sucursal;
import com.neoris.neoirs.service.IProducto;

@Service
public class ProductoImpl implements IProducto {

    @Autowired
    private ProductoDao productoDao;
    @Autowired
    private SucursalDao sucursalDao;

    
    @Transactional
    @Override
    public Producto save(Producto producto){
        return productoDao.save(producto);
    }
    @Transactional
    @Override
    public Producto findById(Integer id){
        return productoDao.findById(id).orElse(null);
    }
    @Transactional
    @Override
    public void delete(Producto producto){
        productoDao.delete(producto);
    }

    @Transactional
    @Override
    public List<Producto> findMaxStockProductBySucursalForFranquicia(String franquiciaId) {
        return productoDao.findMaxStockProductBySucursalForFranquicia(franquiciaId);
    }

    
    @Transactional
    @Override
    public Optional<Producto> findByIdAndSucursal(Integer id, Integer sucursalId) {
        return productoDao.findByIdAndSucursalId(id, sucursalId);
    }
}
