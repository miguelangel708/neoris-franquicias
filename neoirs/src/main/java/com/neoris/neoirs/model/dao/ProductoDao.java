package com.neoris.neoirs.model.dao;
import com.neoris.neoirs.model.entity.Producto;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductoDao extends CrudRepository<Producto, Integer>{

}
