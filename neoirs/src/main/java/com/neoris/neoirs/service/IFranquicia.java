package com.neoris.neoirs.service;

import com.neoris.neoirs.model.entity.Franquicia;

// interfaz de la franquicia
public interface IFranquicia {


    Franquicia save(Franquicia franquicia);

    Franquicia findById(Integer id);

    void delete(Franquicia franquicia);

}
