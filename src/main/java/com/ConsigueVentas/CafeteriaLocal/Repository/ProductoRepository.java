package com.ConsigueVentas.CafeteriaLocal.Repository;

import com.ConsigueVentas.CafeteriaLocal.Entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long>{
    List<Producto> findByCategoria_NombreAndActivo(String nombreCategoria, Boolean activo);

    List<Producto> findByCategoria_Nombre(String categoria);

    List<Producto> findByActivo(Boolean activo);
}
