package com.ceiba.factura.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionLongitudValor;
import com.ceiba.dominio.excepcion.ExcepcionViolaContraint;
import com.ceiba.factura.modelo.entidad.Factura;
import static org.mockito.Mockito.spy;
import com.ceiba.factura.puerto.repositorio.RepositorioFactura;
import com.ceiba.factura.servicio.testdatabuilder.FacturaTestDataBuilder;
import com.ceiba.vehiculo.modelo.entidad.Vehiculo;
import com.ceiba.vehiculo.puerto.repositorio.RepositorioVehiculo;
import com.ceiba.vehiculo.servicio.ServicioEliminarVehiculo;
import com.ceiba.vehiculo.servicio.testdatabuilder.VehiculoTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServicioCrearFacturaTest {

    @Test
    @DisplayName("Deberia lanzar una excepecion cuando la longitud de la placa sea menor a 6")
    void deberiaLanzarUnaExepcionCuandoLaLongitudDeLaPlacaSeaMenorASeis() {
        // arrange
        FacturaTestDataBuilder facturaTestDataBuilder = new FacturaTestDataBuilder().conPlacaFK("124");
        // act - assert
        BasePrueba.assertThrows(facturaTestDataBuilder::build, ExcepcionLongitudValor.class, "La placa debe tener una longitud mayor o igual a 6");
    }

    @Test
    @DisplayName("Deberia lanzar una exepcion cuando se valide la existencia del la placa del vehiculo para generar la factura y siga parqueado")
    void deberiaLanzarUnaExepcionCuandoSeValideLaExistenciaDeLaPlacaDelVehiculoParaGenerarLaFacturaYSigaParqueado() {
        // arrange
        Factura factura = new FacturaTestDataBuilder().build();
        RepositorioFactura repositorioFactura = Mockito.mock(RepositorioFactura.class);
        Mockito.when(repositorioFactura.existe(Mockito.anyString())).thenReturn(true);
        ServicioCrearFactura servicioCrearFactura = new ServicioCrearFactura(repositorioFactura);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearFactura.ejecutar(factura), ExcepcionDuplicidad.class, "el vehiculo sigue estacionado en el parqueadero");
    }

    @Test
    @DisplayName("Deberia lanzar una exepcion cuando se valide la existencia del la placa del vehiculo para generar la factura cuando no hay vehiculo registrado")
    void deberiaLanzarUnaExepcionCuandoSeValideLaExistenciaDeLaPlacaDelVehiculoParaGenerarLaFacturaCuandoNoHayVehiculoRegistrado() {
        // arrange
        Factura factura = new FacturaTestDataBuilder().build();
        RepositorioFactura repositorioFactura = Mockito.mock(RepositorioFactura.class);
        Mockito.when(repositorioFactura.existeVehiculo(Mockito.anyString())).thenReturn(false);
        ServicioCrearFactura servicioCrearFactura = new ServicioCrearFactura(repositorioFactura);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearFactura.ejecutar(factura), ExcepcionViolaContraint.class, "el vehiculo no esta registrado");
    }

    @Test
    @DisplayName("Deberia Crear la factura de manera correcta")
    void deberiaCrearLaFacturaDeManeraCorrecta() {
        // arrange
        Factura factura = new FacturaTestDataBuilder().conPlacaFK("HHHV18G").build();
        RepositorioFactura repositorioFactura = Mockito.mock(RepositorioFactura.class);
        Mockito.when(repositorioFactura.existe(Mockito.anyString())).thenReturn(false);
        Mockito.when(repositorioFactura.existeVehiculo(Mockito.anyString())).thenReturn(true);
        Mockito.when(repositorioFactura.crear(factura)).thenReturn(10L);
        ServicioCrearFactura servicioCrearFactura = new ServicioCrearFactura(repositorioFactura);
        // act
        Long id_factura = servicioCrearFactura.ejecutar(factura);
        //- assert
        assertEquals(10L, id_factura);
        Mockito.verify(repositorioFactura, Mockito.times(1)).crear(factura);
    }

}


