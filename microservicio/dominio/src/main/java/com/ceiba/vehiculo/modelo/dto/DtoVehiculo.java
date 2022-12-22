package com.ceiba.vehiculo.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoVehiculo {
    private String placa;
    private Long idPropietario;
    private Long telefonoContacto;
}


