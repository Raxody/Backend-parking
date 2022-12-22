package com.ceiba.vehiculo.servicio.testdatabuilder;

import com.ceiba.vehiculo.modelo.entidad.Vehiculo;

public class VehiculoTestDataBuilder {

    private String placa;
    private Long idPropietario;
    private Long telefonoContacto;

    public VehiculoTestDataBuilder() {
        placa = "HKZ123";
        idPropietario = 1000493015L;
        telefonoContacto = 3152546967L;
    }

    public VehiculoTestDataBuilder conPlaca(String placa) {
        this.placa = placa;
        return this;
    }

    public VehiculoTestDataBuilder conIdPropietario(Long idPropietario) {
        this.idPropietario = idPropietario;
        return this;
    }

    public VehiculoTestDataBuilder conTelefonoContacto(Long telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
        return this;
    }

    public Vehiculo build() {
        return new Vehiculo(placa,idPropietario, telefonoContacto);
    }
}
