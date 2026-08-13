package com.ConsigueVentas.CafeteriaLocal.Service.Interface;

import com.ConsigueVentas.CafeteriaLocal.Entity.Categoria;

import java.util.List;

public interface ICategoriaService {

    //Create
    Categoria createCategoria(Categoria categoria);

    //Read
    List<Categoria> getAllCategoria();
    Categoria getCategoriaoById(Long id);

    //Update
    Categoria updateCategoria(Long id, Categoria categoria);

    //Delete
    void deleteCategoria(Long id);
}
