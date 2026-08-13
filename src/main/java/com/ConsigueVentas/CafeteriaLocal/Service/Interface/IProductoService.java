package com.ConsigueVentas.CafeteriaLocal.Service.Interface;

import com.ConsigueVentas.CafeteriaLocal.Entity.Producto;

import java.util.List;

public interface IProductoService {

    //Create
    Producto createProducto(Producto producto);

    //Read
    List<Producto> getAllProducto();
    Producto getProductoById(Long id);

    //Update
    Producto updateProducto(Long id, Producto producto);

    //Delete
    void deleteProducto(Long id);
}
