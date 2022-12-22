package com.ceiba.factura.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionSobrepasaAnio;
import com.ceiba.factura.modelo.CalculoValorParqueadero;
import com.ceiba.factura.modelo.entidad.Factura;
import com.ceiba.factura.puerto.dao.DaoFactura;
import com.ceiba.factura.puerto.repositorio.RepositorioFactura;

public class ServicioAsignarDiasParqueoFactura {

    private static final String EL_VEHICULO_NO_ESTA_ESTACIONADO_EN_EL_PARQUEADERO = "El vehciulo no esta estacionado en el parqueadero";
    private static final String SOBREPASA_EL_ANIO_PERMITIDO = "Sobrepasa el anio permitido";
    private final RepositorioFactura repositorioFactura;
    private final DaoFactura daoFactura;

    public ServicioAsignarDiasParqueoFactura(RepositorioFactura repositorioFactura, DaoFactura daoFactura) {
        this.repositorioFactura = repositorioFactura;
        this.daoFactura = daoFactura;
    }



    public void ejecutar(Factura factura) {
        validarExistenciaPrevia(factura);
        for (int i = daoFactura.listar().size()-1; i >=0 ; i--) {
            if (factura.getPlacaFk().equals(daoFactura.listar().get(i).getPlacaFk())) {
                factura.setFechaIngreso(daoFactura.listar().get(i).getFechaIngreso());
                break;
            }
        }

        CalculoValorParqueadero calculoValorParqueadero = new CalculoValorParqueadero(factura.getFechaIngreso());
        if (calculoValorParqueadero.obtenerValorPagoPorDiasParqueo(factura.getCantDias()) == -1) {
            throw new ExcepcionSobrepasaAnio(SOBREPASA_EL_ANIO_PERMITIDO);
        } else {
            factura.setValor(calculoValorParqueadero.obtenerValorPagoPorDiasParqueo(factura.getCantDias()));
            this.repositorioFactura.actualizar(factura);
        }
    }

    private void validarExistenciaPrevia(Factura factura) {
        boolean existe = this.repositorioFactura.existe(factura.getPlacaFk());

        if (!existe) {
            throw new ExcepcionDuplicidad(EL_VEHICULO_NO_ESTA_ESTACIONADO_EN_EL_PARQUEADERO);
        }
    }
}
