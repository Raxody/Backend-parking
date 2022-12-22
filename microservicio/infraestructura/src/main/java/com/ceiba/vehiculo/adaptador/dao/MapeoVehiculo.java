package com.ceiba.vehiculo.adaptador.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.vehiculo.modelo.dto.DtoVehiculo;
import org.springframework.jdbc.core.RowMapper;

public class MapeoVehiculo implements RowMapper<DtoVehiculo>, MapperResult {

    @Override
    public DtoVehiculo mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        String placa = resultSet.getString("placa");
        Long identificacion = resultSet.getLong("identificacion");
        Long telefonoContacto = resultSet.getLong("telefono_contacto");

        return new DtoVehiculo(placa,identificacion,telefonoContacto);
    }

}
