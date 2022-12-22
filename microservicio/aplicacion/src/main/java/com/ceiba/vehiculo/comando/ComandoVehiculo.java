package com.ceiba.vehiculo.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoVehiculo {

    private String placa;
    private Long identificacion;
    private Long telefonoContacto;
}
