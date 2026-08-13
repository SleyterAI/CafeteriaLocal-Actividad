package com.ConsigueVentas.CafeteriaLocal.Repository;

import com.ConsigueVentas.CafeteriaLocal.Entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
