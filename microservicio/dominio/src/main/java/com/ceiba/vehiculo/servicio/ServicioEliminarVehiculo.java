package com.ceiba.vehiculo.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.vehiculo.puerto.dao.DaoVehiculo;
import com.ceiba.vehiculo.puerto.repositorio.RepositorioVehiculo;

public class ServicioEliminarVehiculo {

    private final RepositorioVehiculo repositorioVehiculo;
    private static final String EL_VEHICULO_NO_EXISTE_EN_EL_SISTEMA = "El vehciulo no existe en el sistema";

    public ServicioEliminarVehiculo(RepositorioVehiculo repositorioVehiculo) {
        this.repositorioVehiculo = repositorioVehiculo;
    }

    public void ejecutar(String id) {
        validarExistenciaPrevia(id);
        this.repositorioVehiculo.eliminar(id);
    }

    private void validarExistenciaPrevia(String placa) {
        boolean existe = this.repositorioVehiculo.existe(placa);
        if(!existe) {
            throw new ExcepcionDuplicidad(EL_VEHICULO_NO_EXISTE_EN_EL_SISTEMA);
        }
    }
}

