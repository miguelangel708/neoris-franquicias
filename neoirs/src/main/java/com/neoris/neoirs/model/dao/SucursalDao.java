package com.neoris.neoirs.model.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.neoris.neoirs.model.entity.Sucursal;
import java.util.List;

public interface SucursalDao extends JpaRepository<Sucursal, Integer> {
}
