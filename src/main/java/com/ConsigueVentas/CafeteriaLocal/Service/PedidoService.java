package com.ConsigueVentas.CafeteriaLocal.Service;

import com.ConsigueVentas.CafeteriaLocal.Dto.DetallePedidoRequestDto;
import com.ConsigueVentas.CafeteriaLocal.Dto.PedidoRequestDto;
import com.ConsigueVentas.CafeteriaLocal.Entity.DetallePedido;
import com.ConsigueVentas.CafeteriaLocal.Entity.Pedido;
import com.ConsigueVentas.CafeteriaLocal.Entity.Producto;
import com.ConsigueVentas.CafeteriaLocal.Repository.PedidoRepository;
import com.ConsigueVentas.CafeteriaLocal.Repository.ProductoRepository;
import com.ConsigueVentas.CafeteriaLocal.Service.Interface.IPedidoService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class PedidoService implements IPedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;

    public PedidoService(PedidoRepository pedidoRepository, ProductoRepository productoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.productoRepository = productoRepository;
    }

    /*@Override
    public Pedido createPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }*/

    @Override
    @Transactional
    public Pedido createPedido(PedidoRequestDto pedidoRequest) {

        Pedido pedido = new Pedido();

        //datos q vienen del request
        pedido.setClienteNombre(pedidoRequest.getClienteNombre());
        pedido.setCelular(pedidoRequest.getCelular());
        pedido.setDireccion(pedidoRequest.getDireccion());

        //datos backend no request
        pedido.setFecha(LocalDate.now());
        pedido.setEstado("PENDIENTE");

        //se crea el total tipo BigDecimal
        BigDecimal total = BigDecimal.ZERO;

        //recorrido de la lista detallePedidoRequestDto que se encuentra en pedidoRequest
        for (DetallePedidoRequestDto detallePedidoRequestDto : pedidoRequest.getDetallePedidoRequestDto()) {

            //guardar producto y validar stock
            Producto producto = obtenerProductoYValidarStock(detallePedidoRequestDto);

            Integer cantidad = detallePedidoRequestDto.getCantidad();

            //se obtiene el precio por backend de la bd
            BigDecimal precioUnitario = producto.getPrecio();
            //se calcula el subtotal del pedido
            BigDecimal subtotal = precioUnitario.multiply(
                    BigDecimal.valueOf(detallePedidoRequestDto.getCantidad()));

            //se crea el detallePedido
            DetallePedido detallePedido = new DetallePedido();
            detallePedido.setCantidad(detallePedidoRequestDto.getCantidad());
            detallePedido.setPrecioUnitario(precioUnitario);
            detallePedido.setSubTotal(subtotal);
            detallePedido.setProducto(producto);
            detallePedido.setPedido(pedido);

            //se agrega el detallePedido al pedido general
            pedido.getDetalles().add(detallePedido);

            //se calcula el total segun los subtotal
            total = total.add(subtotal);
            // Descontar stock
            producto.setStock(producto.getStock() - cantidad);
        }

        //se añade el total al pedido general
        pedido.setTotal(total);

        //se guarda el pedido
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

    private Producto obtenerProductoYValidarStock(
            DetallePedidoRequestDto detalleDto) {

        Producto producto = productoRepository
                .findById(detalleDto.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Integer cantidad = detalleDto.getCantidad();

        if (producto.getStock() < cantidad) {
            throw new RuntimeException("Stock insuficiente para el producto: " + producto.getNombre()
            + ". Stock disponible: " + producto.getStock() + ", cantidad solicitada: " + cantidad);
        }
        return producto;
    }
}
