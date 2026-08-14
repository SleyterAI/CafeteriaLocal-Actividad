package com.ConsigueVentas.CafeteriaLocal.Service;

import com.ConsigueVentas.CafeteriaLocal.Entity.Pedido;
import com.ConsigueVentas.CafeteriaLocal.Entity.Producto;
import com.ConsigueVentas.CafeteriaLocal.Repository.ProductoRepository;
import com.ConsigueVentas.CafeteriaLocal.Service.Interface.IProductoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public Producto createProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public List<Producto> getAllProducto() {
        return productoRepository.findAll();
    }

    @Override
    public Producto getProductoById(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("El producto no existe")
                );
    }

    @Override
    public Producto updateProducto(Long id, Producto producto) {
        Producto productoUpdate = productoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("El producto no existe")
                );
        productoUpdate.setNombre(producto.getNombre());
        productoUpdate.setDescripcion(producto.getDescripcion());
        productoUpdate.setPrecio(producto.getPrecio());
        productoUpdate.setStock(producto.getStock());
        productoUpdate.setImageUrl(producto.getImageUrl());
        productoUpdate.setActivo(producto.getActivo());
        productoUpdate.setCategoria(producto.getCategoria());

        return productoRepository.save(productoUpdate);
    }

    @Override
    public void deleteProducto(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("El producto no existe");
        }
        productoRepository.deleteById(id);
    }


    public List<Producto> filtrarProductos(String nombreCategoria, Boolean activo) {
        if (nombreCategoria != null && activo != null) {
            return productoRepository.findByCategoria_NombreAndActivo(nombreCategoria, activo);
        } else if (nombreCategoria != null) {
            return productoRepository.findByCategoria_Nombre(nombreCategoria);
        } else if (activo != null) {
            return productoRepository.findByActivo(activo);
        }
        return productoRepository.findAll();
    }

    public Producto cambiarActivo(Long id, Boolean nuevoActivo) {

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        producto.setActivo(nuevoActivo);

        return productoRepository.save(producto);
    }
}
