package com.ceiba.vehiculo.consulta;

import java.util.List;

import com.ceiba.vehiculo.puerto.dao.DaoVehiculo;
import org.springframework.stereotype.Component;
import com.ceiba.vehiculo.modelo.dto.DtoVehiculo;

@Component
public class ManejadorListarVehiculo {

    private final DaoVehiculo daoVehiculo;

    public ManejadorListarVehiculo(DaoVehiculo daoVehiculo){
        this.daoVehiculo = daoVehiculo;
    }

    public List<DtoVehiculo> ejecutar(){ return this.daoVehiculo.listar(); }
}

