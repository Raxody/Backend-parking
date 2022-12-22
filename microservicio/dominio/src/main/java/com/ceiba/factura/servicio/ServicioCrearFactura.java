package com.ceiba.factura.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionViolaContraint;
import com.ceiba.factura.modelo.entidad.Factura;
import com.ceiba.factura.puerto.repositorio.RepositorioFactura;

public class ServicioCrearFactura {

    private static final String EL_VEHICULO_SIGUE_ESTACIONADO_EN_EL_PARQUEADERO ="el vehiculo sigue estacionado en el parqueadero";
    private static final String EL_VEHICULO_NO_ESTA_REGISTRADO ="el vehiculo no esta registrado";
    private final RepositorioFactura repositorioFactura;

    public ServicioCrearFactura(RepositorioFactura repositorioFactura) {
        this.repositorioFactura = repositorioFactura;
    }

    public Long ejecutar(Factura factura) {
        validarExistenciaPrevia(factura);
        validarExistenciaPreviaVehiculo(factura);
        return this.repositorioFactura.crear(factura);
    }

    private void validarExistenciaPrevia(Factura factura) {
        boolean existe = this.repositorioFactura.existe(factura.getPlacaFk());
        if(existe) {
            throw new ExcepcionDuplicidad(EL_VEHICULO_SIGUE_ESTACIONADO_EN_EL_PARQUEADERO);
        }
    }

    private void validarExistenciaPreviaVehiculo(Factura factura) {
        boolean existe = this.repositorioFactura.existeVehiculo(factura.getPlacaFk());
        if(!existe) {
            throw new ExcepcionViolaContraint(EL_VEHICULO_NO_ESTA_REGISTRADO);
        }
    }

}