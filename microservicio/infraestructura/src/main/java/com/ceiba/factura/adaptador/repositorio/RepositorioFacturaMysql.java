package com.ceiba.factura.adaptador.repositorio;

import com.ceiba.factura.modelo.entidad.Factura;
import com.ceiba.factura.puerto.repositorio.RepositorioFactura;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioFacturaMysql implements RepositorioFactura {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="consultas", value="crearFactura")
    private static String sqlCrear;

    @SqlStatement(namespace="consultas", value="calcularDiasValorFactura")
    private static String sqlCalcularDiasValor;

    @SqlStatement(namespace="consultas", value="existeFactura")
    private static String sqlExiste;

    @SqlStatement(namespace="consultas", value="existe")
    private static String sqlExisteVehiculo;

    public RepositorioFacturaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(Factura factura) {
            return this.customNamedParameterJdbcTemplate.crear(factura, sqlCrear,"id_factura");
    }


    @Override
    public boolean existe(String placaFk) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("placaFk", placaFk);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExiste,paramSource, Boolean.class);
    }

    @Override
    public boolean existeVehiculo(String placa) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("placa", placa);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExisteVehiculo,paramSource, Boolean.class);
    }

    @Override
    public void actualizar(Factura factura) {
        this.customNamedParameterJdbcTemplate.actualizar(factura, sqlCalcularDiasValor);
    }

}

