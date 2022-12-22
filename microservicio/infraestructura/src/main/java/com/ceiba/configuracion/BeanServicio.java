package com.ceiba.configuracion;

import com.ceiba.factura.puerto.dao.DaoFactura;
import com.ceiba.factura.puerto.repositorio.RepositorioFactura;
import com.ceiba.factura.servicio.ServicioAsignarDiasParqueoFactura;
import com.ceiba.factura.servicio.ServicioCrearFactura;
import com.ceiba.vehiculo.puerto.repositorio.RepositorioVehiculo;
import com.ceiba.vehiculo.servicio.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BeanServicio {

    @Bean
    public ServicioCrearVehiculo servicioCrearVehiculo(RepositorioVehiculo repositorioVehiculo) {
        return new ServicioCrearVehiculo(repositorioVehiculo);
    }

    @Bean
    public ServicioEliminarVehiculo servicioEliminarVehiculo(RepositorioVehiculo repositorioVehiculo) {
        return new ServicioEliminarVehiculo(repositorioVehiculo);
    }

    @Bean
    public ServicioActualizarVehiculo servicioActualizarVehiculo(RepositorioVehiculo repositorioVehiculo) {
        return new ServicioActualizarVehiculo(repositorioVehiculo);
    }

    @Bean
        public ServicioAsignarDiasParqueoFactura servicioAsignarDiasParqueoFactura(RepositorioFactura repositorioFactura, DaoFactura daoFactura) {
        return new ServicioAsignarDiasParqueoFactura(repositorioFactura,daoFactura);
    }

    @Bean
    public ServicioCrearFactura servicioCrearFactura(RepositorioFactura repositorioFactura) {
        return new ServicioCrearFactura(repositorioFactura);
    }


}