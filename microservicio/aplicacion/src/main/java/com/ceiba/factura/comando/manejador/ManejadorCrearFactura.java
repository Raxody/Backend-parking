package com.ceiba.factura.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.factura.ComandoFacturaAsignarDias;
import com.ceiba.factura.ComandoFacturaCrear;
import com.ceiba.factura.comando.fabrica.FabricaFactura;
import com.ceiba.factura.modelo.entidad.Factura;
import com.ceiba.factura.servicio.ServicioCrearFactura;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCrearFactura {

    private final FabricaFactura fabricaFactura;
    private final ServicioCrearFactura servicioCrearFactura;

    public ManejadorCrearFactura(FabricaFactura fabricaFactura, ServicioCrearFactura servicioCrearFactura) {
        this.fabricaFactura = fabricaFactura;
        this.servicioCrearFactura = servicioCrearFactura;
    }

    public ComandoRespuesta<Long> ejecutar(ComandoFacturaCrear comandoFacturaCrear) {
        Factura factura = this.fabricaFactura.crear(comandoFacturaCrear);
        return new ComandoRespuesta<>(this.servicioCrearFactura.ejecutar(factura));
    }
}
