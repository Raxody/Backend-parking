package com.ceiba.factura.adaptador.dao;

import com.ceiba.factura.modelo.dto.DtoFacturaAsignarDias;
import com.ceiba.factura.puerto.dao.DaoFactura;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoFacturaMysql implements DaoFactura {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "consultas", value = "listarFactura")
    private static String sqlListar;

    public DaoFacturaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoFacturaAsignarDias> listar() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar, new MapeoFactura());
    }
}
