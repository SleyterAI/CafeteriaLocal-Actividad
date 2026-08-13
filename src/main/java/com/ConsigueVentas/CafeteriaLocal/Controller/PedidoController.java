package com.ConsigueVentas.CafeteriaLocal.Controller;

import com.ConsigueVentas.CafeteriaLocal.Entity.Pedido;
import com.ConsigueVentas.CafeteriaLocal.Service.PedidoService;
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
    public ResponseEntity<Pedido> createStudent(@RequestBody Pedido pedido) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pedidoService.createPedido(pedido));
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> getAllPedido() {
        return ResponseEntity.ok(pedidoService.getAllPedido());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.getPedidoById(id));
    }

}
