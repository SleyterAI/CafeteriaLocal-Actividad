package com.ConsigueVentas.CafeteriaLocal.Service;

import com.ConsigueVentas.CafeteriaLocal.Entity.Pedido;
import com.ConsigueVentas.CafeteriaLocal.Repository.PedidoRepository;
import com.ConsigueVentas.CafeteriaLocal.Service.Interface.IPedidoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService implements IPedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public Pedido createPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public List<Pedido> getAllPedido() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido getPedidoById(Long id) {
        return pedidoRepository.findById(id).
                orElseThrow(() ->
                        new RuntimeException("El pedido no existe")
                );
    }

    @Override
    public Pedido updatePedido(Long id, Pedido pedido) {
        Pedido pedidoUpdate = pedidoRepository.findById(id).
                orElseThrow(() ->
                        new RuntimeException("El pedido no existe")
                );
        pedidoUpdate.setClienteNombre(pedido.getClienteNombre());
        pedidoUpdate.setCelular(pedido.getCelular());
        pedidoUpdate.setDireccion(pedido.getDireccion());
        pedidoUpdate.setFecha(pedido.getFecha());
        pedidoUpdate.setEstado(pedido.getEstado());
        pedidoUpdate.setTotal(pedido.getTotal());
        return pedidoRepository.save(pedidoUpdate);
    }

    @Override
    public void deletePedido(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new RuntimeException("El pedido no existe");
        }
        pedidoRepository.deleteById(id);
    }

    public Pedido cambiarEstado(Long id, String nuevoEstado) {

        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        pedido.setEstado(nuevoEstado);

        return pedidoRepository.save(pedido);
    }
}
