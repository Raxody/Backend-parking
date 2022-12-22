package com.ceiba.factura.comando.fabrica;

import com.ceiba.factura.ComandoFacturaAsignarDias;
import com.ceiba.factura.ComandoFacturaCrear;
import com.ceiba.factura.modelo.entidad.Factura;
import org.springframework.stereotype.Component;

@Component
public class FabricaFactura {

    public Factura crear(ComandoFacturaAsignarDias comandoFacturaAsignarDias) {
        return new Factura(
                comandoFacturaAsignarDias.getIdFactura(),
                comandoFacturaAsignarDias.getFechaIngreso(),
                comandoFacturaAsignarDias.getCantDias(),
                comandoFacturaAsignarDias.getValor(),
                comandoFacturaAsignarDias.getPlacaFk()
        );
    }

    public Factura crear(ComandoFacturaCrear comandoFacturaCrear) {
        return new Factura(
                comandoFacturaCrear.getPlacaFk()
        );
    }

}