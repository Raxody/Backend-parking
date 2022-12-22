package com.ceiba.vehiculo.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.*;
import com.ceiba.vehiculo.modelo.entidad.Vehiculo;
import com.ceiba.vehiculo.puerto.repositorio.RepositorioVehiculo;
import com.ceiba.vehiculo.servicio.testdatabuilder.VehiculoTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ServicioActualizarVehiculoTest {

    @Test
    @DisplayName("Deberia validar la existencia previa del usuario")
    void deberiaValidarLaExistenciaPreviaDelVehiculo() {
        // arrange
        Vehiculo vehiculo = new VehiculoTestDataBuilder().conPlaca("HKZ123").build();
        RepositorioVehiculo repositorioVehiculo = Mockito.mock(RepositorioVehiculo.class);
        Mockito.when(repositorioVehiculo.existe(Mockito.anyString())).thenReturn(false);
        ServicioActualizarVehiculo servicioActualizarVehiculo = new ServicioActualizarVehiculo(repositorioVehiculo);
        // act - assert
        BasePrueba.assertThrows(() -> servicioActualizarVehiculo.ejecutar(vehiculo), ExcepcionDuplicidad.class,"El vehciulo no existe en el sistema");
    }

    @Test
    @DisplayName("Deberia actualizar correctamente en el repositorio")
    void deberiaActualizarCorrectamenteEnElRepositorio() {
        // arrange
        Vehiculo vehiculo = new VehiculoTestDataBuilder().conPlaca("HKZ123").build();
        RepositorioVehiculo repositorioVehiculo = Mockito.mock(RepositorioVehiculo.class);
        Mockito.when(repositorioVehiculo.existe(Mockito.anyString())).thenReturn(true);
        ServicioActualizarVehiculo servicioActualizarVehiculo = new ServicioActualizarVehiculo(repositorioVehiculo);
        // act
        servicioActualizarVehiculo.ejecutar(vehiculo);
        //assert
        Mockito.verify(repositorioVehiculo,Mockito.times(1)).actualizar(vehiculo);
    }
}
