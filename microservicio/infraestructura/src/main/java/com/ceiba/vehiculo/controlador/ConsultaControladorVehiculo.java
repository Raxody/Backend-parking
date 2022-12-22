package com.ceiba.vehiculo.controlador;

import java.util.List;

import com.ceiba.vehiculo.consulta.ManejadorListarVehiculo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.vehiculo.modelo.dto.DtoVehiculo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/listarVehiculo")
//http://localhost:8083/parqueadero/listarVehiculo
@Api(tags={"Controlador consulta usuario"})
public class ConsultaControladorVehiculo {

    private final ManejadorListarVehiculo manejadorListarVehiculo;

    public ConsultaControladorVehiculo(ManejadorListarVehiculo manejadorListarVehiculo) {
        this.manejadorListarVehiculo = manejadorListarVehiculo;
    }

    @GetMapping
    @ApiOperation("Listar vehiculos")
    public List<DtoVehiculo> listar() {
        return this.manejadorListarVehiculo.ejecutar();
    }
}
