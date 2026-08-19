package com.ConsigueVentas.CafeteriaLocal.Controller;

import com.ConsigueVentas.CafeteriaLocal.Dto.DetallePedidoResponseDto;
import com.ConsigueVentas.CafeteriaLocal.Dto.PedidoRequestDto;
import com.ConsigueVentas.CafeteriaLocal.Dto.PedidoResponseDto;
import com.ConsigueVentas.CafeteriaLocal.Entity.Pedido;
import com.ConsigueVentas.CafeteriaLocal.Service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<PedidoResponseDto> createPedido(@Valid @RequestBody PedidoRequestDto pedidoRequestDto) {
        Pedido pedido = pedidoService.createPedido(pedidoRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(pedido));
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> getAllPedido() {
        return ResponseEntity.ok(pedidoService.getAllPedido());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.getPedidoById(id));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Pedido> cambiarEstado(
            @PathVariable Long id, @RequestBody PedidoResponseDto nuevoEstado) {

        Pedido pedido = pedidoService.cambiarEstado(id, nuevoEstado.getEstado());

        return ResponseEntity.ok(pedido);
    }

    private PedidoResponseDto toResponse(Pedido pedido){
        PedidoResponseDto pedidoResponseDto = new PedidoResponseDto();
        pedidoResponseDto.setClienteNombre(pedido.getClienteNombre());
        pedidoResponseDto.setCelular(pedido.getCelular());
        pedidoResponseDto.setDireccion(pedido.getDireccion());
        pedidoResponseDto.setFecha(pedido.getFecha());
        pedidoResponseDto.setEstado(pedido.getEstado());
        pedidoResponseDto.setTotal(pedido.getTotal());

        List<DetallePedidoResponseDto> detalles =
                pedido.getDetalles()
                .stream()
                .map(detalle -> {
                    DetallePedidoResponseDto detalleResponse =
                            new DetallePedidoResponseDto();

                    detalleResponse.setProductoId(detalle.getProducto().getId());
                    detalleResponse.setProductoNombre(detalle.getProducto().getNombre());
                    detalleResponse.setCantidad(detalle.getCantidad());
                    detalleResponse.setPrecioUnitario(detalle.getPrecioUnitario());
                    detalleResponse.setSubTotal(detalle.getSubTotal());
                    return detalleResponse;})
                .toList();
        pedidoResponseDto.setDetalles(detalles);
        return pedidoResponseDto;
    }

}
