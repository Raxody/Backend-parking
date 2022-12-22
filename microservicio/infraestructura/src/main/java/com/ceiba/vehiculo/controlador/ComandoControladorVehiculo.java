package com.ceiba.vehiculo.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.vehiculo.comando.ComandoVehiculo;
import com.ceiba.vehiculo.comando.manejador.ManejadorActualizarVehiculo;
import com.ceiba.vehiculo.comando.manejador.ManejadorCrearVehiculo;
import com.ceiba.vehiculo.comando.manejador.ManejadorEliminarVehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/vehiculoVariado")

@Api(tags = { "Controlador comando vehiculo"})
public class ComandoControladorVehiculo {

	private final ManejadorCrearVehiculo manejadorCrearVehiculo;
	private final ManejadorEliminarVehiculo manejadorEliminarVehiculo;
	private final ManejadorActualizarVehiculo manejadorActualizarVehiculo;

	@Autowired
	public ComandoControladorVehiculo(ManejadorCrearVehiculo manejadorCrearVehiculo,
									  ManejadorEliminarVehiculo manejadorEliminarVehiculo,
									  ManejadorActualizarVehiculo manejadorActualizarVehiculo) {
		this.manejadorCrearVehiculo = manejadorCrearVehiculo;
		this.manejadorEliminarVehiculo = manejadorEliminarVehiculo;
		this.manejadorActualizarVehiculo = manejadorActualizarVehiculo;
	}

	@PostMapping("/agregar")
	@ApiOperation("Crear vehiculo")
	public ComandoRespuesta<Long> crear(@RequestBody ComandoVehiculo comandoVehiculo) {
		return manejadorCrearVehiculo.ejecutar(comandoVehiculo);
	}

	@DeleteMapping(value="/borrar/{placa}")
	@ApiOperation("Eliminar vehiculo")
	public void eliminar(@PathVariable String placa) {
		manejadorEliminarVehiculo.ejecutar(placa);
	}


	@PutMapping(value="/actualizar/{placa}")
	@ApiOperation("Actualizar Usuario")
	public void actualizar(@RequestBody ComandoVehiculo comandoVehiculo, @PathVariable String placa) {
		comandoVehiculo.setPlaca(placa);

		manejadorActualizarVehiculo.ejecutar(comandoVehiculo);
	}
}

