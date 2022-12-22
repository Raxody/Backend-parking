package com.ceiba.factura.comando.manejador;

import com.ceiba.factura.ComandoFacturaAsignarDias;
import com.ceiba.factura.comando.fabrica.FabricaFactura;
import com.ceiba.factura.modelo.entidad.Factura;
import com.ceiba.factura.puerto.dao.DaoFactura;
import com.ceiba.factura.servicio.ServicioAsignarDiasParqueoFactura;
import com.ceiba.manejador.ManejadorComando;
import org.springframework.stereotype.Component;

@Component
public class ManejadorAsignarDiasParqueoFactura implements ManejadorComando<ComandoFacturaAsignarDias> {

    private final FabricaFactura fabricaFactura;
    private final ServicioAsignarDiasParqueoFactura servicioAsignarDiasParqueoFactura;

    public ManejadorAsignarDiasParqueoFactura(FabricaFactura fabricaFactura, ServicioAsignarDiasParqueoFactura servicioAsignarDiasParqueoFactura, DaoFactura daoFactura) {
        this.fabricaFactura = fabricaFactura;
        this.servicioAsignarDiasParqueoFactura = servicioAsignarDiasParqueoFactura;
    }

    public void ejecutar(ComandoFacturaAsignarDias comandoFacturaAsignarDias) {
        Factura factura = this.fabricaFactura.crear(comandoFacturaAsignarDias);

        this.servicioAsignarDiasParqueoFactura.ejecutar(factura);
    }

}