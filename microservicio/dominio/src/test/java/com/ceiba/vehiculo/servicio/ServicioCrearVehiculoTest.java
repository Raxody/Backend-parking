package com.ceiba.vehiculo.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.*;
import com.ceiba.vehiculo.modelo.entidad.Vehiculo;
import com.ceiba.vehiculo.puerto.repositorio.RepositorioVehiculo;
import com.ceiba.vehiculo.servicio.testdatabuilder.VehiculoTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServicioCrearVehiculoTest {

    @Test
    @DisplayName("Deberia lanzar una exepecion cuando la longitud de la placa sea menor a 6")
    void deberiaLanzarUnaExepcionCuandoLaLongitudDeLaPlacaSeaMenorASeis() {
        // arrange
        VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder().conPlaca("124");
        // act - assert
        BasePrueba.assertThrows(vehiculoTestDataBuilder::build, ExcepcionLongitudValor.class, "La placa debe tener una longitud mayor o igual a 6");
    }

    @Test
    @DisplayName("Deberia lanzar una exepecion cuando la longitud del telefono de contacto sea menor a 10")
    void deberiaLanzarUnaExepcionCuandoLaLongitudDelTelefonoContactoSeaMenorADiez() {
        // arrange
        VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder().conTelefonoContacto(124L);
        // act - assert
        BasePrueba.assertThrows(vehiculoTestDataBuilder::build, ExcepcionLongitudValor.class, "el teléfono de contacto debe tener una longitud mayor o igual a 10");
    }


    @Test
    @DisplayName("Deberia lanzar una exepcion cuando se valide la existencia del la placa del vehiculo")
    void deberiaLanzarUnaExepcionCuandoSeValideLaExistenciaDeLaPlacaDelVehiculo() {
        // arrange
        Vehiculo vehiculo = new VehiculoTestDataBuilder().build();
        RepositorioVehiculo repositorioVehiculo = Mockito.mock(RepositorioVehiculo.class);
        Mockito.when(repositorioVehiculo.existe(Mockito.anyString())).thenReturn(true);
        ServicioCrearVehiculo servicioCrearVehiculo = new ServicioCrearVehiculo(repositorioVehiculo);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearVehiculo.ejecutar(vehiculo), ExcepcionDuplicidad.class,"El vehiculo ya existe en el sistema");
    }

    @Test
    @DisplayName("Deberia Crear el vehiculo de manera correcta")
    void deberiaCrearElVehiculoDeManeraCorrecta() {
        // arrange
        Vehiculo vehiculo = new VehiculoTestDataBuilder().build();
        RepositorioVehiculo repositorioVehiculo = Mockito.mock(RepositorioVehiculo.class);
        Mockito.when(repositorioVehiculo.existe(Mockito.anyString())).thenReturn(false);
        Mockito.when(repositorioVehiculo.crear(vehiculo)).thenReturn(10L);
        ServicioCrearVehiculo servicioCrearVehiculo = new ServicioCrearVehiculo(repositorioVehiculo);
        // act
        Long idPropietario = servicioCrearVehiculo.ejecutar(vehiculo);
        //- assert
        assertEquals(10L,idPropietario);
        Mockito.verify(repositorioVehiculo, Mockito.times(1)).crear(vehiculo);
    }

}
