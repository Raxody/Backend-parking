package com.ceiba.factura.controlador;

import com.ceiba.factura.consulta.ManejadorListarFactura;
import com.ceiba.factura.modelo.dto.DtoFacturaAsignarDias;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/listarFacturas")
//http://localhost:8083/parqueadero/listarFacturas
@Api(tags = {"Controlador consulta facturas"})
public class ConsultaControladorFactura {

    private final ManejadorListarFactura manejadorListarFactura;

    public ConsultaControladorFactura(ManejadorListarFactura manejadorListarFactura) {
        this.manejadorListarFactura = manejadorListarFactura;
    }

    @GetMapping
    @ApiOperation("Listar Usuarios")
    public List<DtoFacturaAsignarDias> listar() {
        return this.manejadorListarFactura.ejecutar();
    }
}
