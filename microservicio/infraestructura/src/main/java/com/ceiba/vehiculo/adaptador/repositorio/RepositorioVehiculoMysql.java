package com.ceiba.vehiculo.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.vehiculo.modelo.entidad.Vehiculo;
import com.ceiba.vehiculo.puerto.repositorio.RepositorioVehiculo;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioVehiculoMysql implements RepositorioVehiculo {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="consultas", value="crear")
    private static String sqlCrear;

    @SqlStatement(namespace="consultas", value="actualizar")
    private static String sqlActualizar;

    @SqlStatement(namespace="consultas", value="eliminar")
    private static String sqlEliminar;

    @SqlStatement(namespace="consultas", value="existe")
    private static String sqlExiste;

    public RepositorioVehiculoMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(Vehiculo vehiculo) {
        return this.customNamedParameterJdbcTemplate.crear(vehiculo, sqlCrear,"identificacion");
    }

    @Override
    public void eliminar(String placa) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("placa", placa);

        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminar, paramSource);
    }

    @Override
    public boolean existe(String placa) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("placa", placa);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExiste,paramSource, Boolean.class);
    }

    @Override
    public void actualizar(Vehiculo vehiculo) {
        this.customNamedParameterJdbcTemplate.actualizar(vehiculo, sqlActualizar);
    }

}
