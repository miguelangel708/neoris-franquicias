package com.neoris.neoirs.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neoris.neoirs.model.dao.SucursalDao;
import com.neoris.neoirs.model.entity.Sucursal;
import com.neoris.neoirs.service.ISucursal;


@Service
public class SucursalImpl implements ISucursal{

    @Autowired
    private SucursalDao sucursalDao;

    @Transactional
    @Override
    public Sucursal save(Sucursal sucursal) {

        return sucursalDao.save(sucursal);
    }

    @Transactional(readOnly = true)
    @Override
    public Sucursal findById(Integer id) {
        return sucursalDao.findById(id).orElse(null);

    }

    @Transactional
    @Override
    public void delete(Sucursal sucursal) {
        sucursalDao.delete(sucursal);
    }
}
