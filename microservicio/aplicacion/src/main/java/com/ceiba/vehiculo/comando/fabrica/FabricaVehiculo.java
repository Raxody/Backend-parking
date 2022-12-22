package com.ceiba.vehiculo.comando.fabrica;

import com.ceiba.vehiculo.modelo.entidad.Vehiculo;
import org.springframework.stereotype.Component;
import com.ceiba.vehiculo.comando.ComandoVehiculo;

@Component
public class FabricaVehiculo {

    public Vehiculo crear(ComandoVehiculo comandoVehiculo) {
        return new Vehiculo(
                comandoVehiculo.getPlaca(),
                comandoVehiculo.getIdentificacion(),
                comandoVehiculo.getTelefonoContacto()
        );
    }

}
