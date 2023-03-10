package com.ceiba.vehiculo.puerto.dao;

import com.ceiba.vehiculo.modelo.dto.DtoVehiculo;

import java.util.List;

public interface DaoVehiculo {

    /**
     * Permite listar los vehiculos
     * @return los Vehiculos
     */
    List<DtoVehiculo> listar();

}
