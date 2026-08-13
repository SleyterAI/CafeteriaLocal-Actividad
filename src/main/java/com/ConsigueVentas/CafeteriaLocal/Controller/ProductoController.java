package com.ConsigueVentas.CafeteriaLocal.Controller;

import com.ConsigueVentas.CafeteriaLocal.Entity.Producto;
import com.ConsigueVentas.CafeteriaLocal.Service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    public ResponseEntity<Producto> createProducto(@Valid @RequestBody Producto producto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productoService.createProducto(producto));
    }

    @GetMapping
    public ResponseEntity<List<Producto>> getAllProducto(
            @RequestParam(required = false) Long categoria) {
        if (categoria != null) {
            return ResponseEntity.ok(productoService.findByCategoriaId(categoria));
        }
        return ResponseEntity.ok(productoService.getAllProducto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.getProductoById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(
            @PathVariable Long id, @Valid @RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.updateProducto(id, producto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        productoService.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }
}
