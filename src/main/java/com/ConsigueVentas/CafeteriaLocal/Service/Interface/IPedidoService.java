package com.ConsigueVentas.CafeteriaLocal.Service.Interface;

import com.ConsigueVentas.CafeteriaLocal.Entity.Pedido;


import java.util.List;

public interface IPedidoService {

    //Create
    Pedido createPedido(Pedido pedido);

    //Read
    List<Pedido> getAllPedido();
    Pedido getPedidoById(Long id);

    //Update
    Pedido updatePedido(Long id, Pedido pedido);

    //Delete
    void deletePedido(Long id);
}
