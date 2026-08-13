package com.ConsigueVentas.CafeteriaLocal.Repository;

import com.ConsigueVentas.CafeteriaLocal.Entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByCategoriaId(Long categoriaId);

}
