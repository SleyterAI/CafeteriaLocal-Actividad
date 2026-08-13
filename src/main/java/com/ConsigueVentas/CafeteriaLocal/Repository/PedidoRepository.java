package com.ConsigueVentas.CafeteriaLocal.Repository;

import com.ConsigueVentas.CafeteriaLocal.Entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
