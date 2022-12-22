package com.ceiba.factura.adaptador.dao;

import com.ceiba.factura.modelo.dto.DtoFacturaAsignarDias;
import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class MapeoFactura implements RowMapper<DtoFacturaAsignarDias>, MapperResult {

    @Override
    public DtoFacturaAsignarDias mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Long factura = resultSet.getLong("id_factura");
        String fechaIngreso = resultSet.getString("fecha_ingreso");
        int cantDias = resultSet.getInt("cant_dias");
        double valor = resultSet.getDouble("valor");
        String placaFk = resultSet.getString("placa_fk");
        return new DtoFacturaAsignarDias(factura, fechaIngreso, cantDias,valor,placaFk);
    }

}
