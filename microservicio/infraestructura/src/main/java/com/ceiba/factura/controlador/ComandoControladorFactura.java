package com.ceiba.factura.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.factura.ComandoFacturaAsignarDias;
import com.ceiba.factura.ComandoFacturaCrear;
import com.ceiba.factura.comando.manejador.ManejadorAsignarDiasParqueoFactura;
import com.ceiba.factura.comando.manejador.ManejadorCrearFactura;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/facturaVariado")
//http://localhost:8083/parqueadero/facturaVariado
@Api(tags = {"Controlador comando factura"})
public class ComandoControladorFactura {

    private final ManejadorCrearFactura manejadorCrearFactura;
    private final ManejadorAsignarDiasParqueoFactura manejadorAsignarDiasParqueoFactura;

    @Autowired
    public ComandoControladorFactura(ManejadorCrearFactura manejadorCrearFactura, ManejadorAsignarDiasParqueoFactura manejadorAsignarDiasParqueoFactura) {
        this.manejadorCrearFactura = manejadorCrearFactura;
        this.manejadorAsignarDiasParqueoFactura = manejadorAsignarDiasParqueoFactura;
    }

    @PostMapping("/agregar")
    @ApiOperation("Registrar factura")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoFacturaCrear comandoFacturaCrear) {
        return manejadorCrearFactura.ejecutar(comandoFacturaCrear);
    }


    @PutMapping(value = "/calcular/{placa}")
    @ApiOperation("Actualizar Usuario")
    public void actualizar(@RequestBody ComandoFacturaAsignarDias comandoFacturaAsignarDias, @PathVariable String placa) {
        comandoFacturaAsignarDias.setPlacaFk(placa);
        manejadorAsignarDiasParqueoFactura.ejecutar(comandoFacturaAsignarDias);
    }
}



