package com.ConsigueVentas.CafeteriaLocal.Dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class PedidoResponseDto {

    private String clienteNombre;
    private String celular;
    private String direccion;
    private LocalDate fecha;
    private String estado;
    private BigDecimal total;

    private List<DetallePedidoResponseDto> detalles;
}
