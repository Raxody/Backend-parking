package com.ceiba.vehiculo.servicio.testdatabuilder;

import com.ceiba.vehiculo.comando.ComandoVehiculo;


public class ComandoVehiculoTestDataBuilder {

    private String placa;
    private Long idPropietario;
    private Long telefono_contacto;

    public ComandoVehiculoTestDataBuilder() {
        placa = "HHHV318G";
        idPropietario = 1000493012l;
        telefono_contacto = 31651651156L;
    }

    public ComandoVehiculoTestDataBuilder conPlaca(String placa) {
        this.placa = placa;
        return this;
    }

    public ComandoVehiculo build() {
        return new ComandoVehiculo(placa,idPropietario, telefono_contacto);
    }
}
