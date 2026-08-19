package com.ConsigueVentas.CafeteriaLocal.Dto;

import com.ConsigueVentas.CafeteriaLocal.Entity.DetallePedido;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PedidoRequestDto {

    @NotBlank
    private String clienteNombre;

    @NotBlank
    @Pattern(regexp = "^[0-9]{9}$",
            message = "El celular debe tener 9 dígitos")
    private String celular;

    @NotBlank
    private String direccion;

    @NotEmpty(message = "El pedido debe tener al menos un producto")
    private List<@Valid DetallePedidoRequestDto> detallePedidoRequestDto = new ArrayList<>();
}
