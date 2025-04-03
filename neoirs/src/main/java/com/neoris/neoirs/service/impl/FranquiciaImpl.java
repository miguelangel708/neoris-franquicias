package com.neoris.neoirs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neoris.neoirs.model.dao.FranquiciaDao;
import com.neoris.neoirs.model.entity.Franquicia;
import com.neoris.neoirs.service.IFranquicia;

@Service
public class FranquiciaImpl implements IFranquicia{

    @Autowired
    private FranquiciaDao franquiciaDao;

    @Transactional
    @Override
    public Franquicia save(Franquicia franquicia) {

        return franquiciaDao.save(franquicia);
    }

    @Transactional(readOnly = true)
    @Override
    public Franquicia findById(Integer id) {
        return franquiciaDao.findById(id).orElse(null);

    }

    @Transactional
    @Override
    public void delete(Franquicia franquicia) {
        franquiciaDao.delete(franquicia);
    }




}
