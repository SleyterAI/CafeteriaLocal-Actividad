package com.ConsigueVentas.CafeteriaLocal.Dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DetallePedidoResponseDto {
    private Long productoId;
    private String productoNombre;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subTotal;
}
