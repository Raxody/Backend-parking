package com.ceiba.vehiculo.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.vehiculo.modelo.entidad.Vehiculo;
import com.ceiba.vehiculo.puerto.repositorio.RepositorioVehiculo;
import com.ceiba.vehiculo.servicio.testdatabuilder.VehiculoTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ServicioEliminarVehiculoTest {
    @Test
    @DisplayName("Deberia eliminar el vehiculo llamando al repositorio")
    void deberiaEliminarElVehiculoLlamandoAlRepositorio() {
        RepositorioVehiculo repositorioVehiculo = Mockito.mock(RepositorioVehiculo.class);
        Mockito.when(repositorioVehiculo.existe(Mockito.anyString())).thenReturn(true);
        ServicioEliminarVehiculo servicioEliminarVehiculo = new ServicioEliminarVehiculo(repositorioVehiculo);
        servicioEliminarVehiculo.ejecutar("HHH412");
        Mockito.verify(repositorioVehiculo, Mockito.times(1)).eliminar("HHH412");

    }

    @Test
    @DisplayName("Deberia validar la existencia previa del usuario")
    void deberiaValidarLaExistenciaPreviaDelVehiculo() {
        // arrange
        Vehiculo vehiculo = new VehiculoTestDataBuilder().conPlaca("HKZ123").build();
        RepositorioVehiculo repositorioVehiculo = Mockito.mock(RepositorioVehiculo.class);
        Mockito.when(repositorioVehiculo.existe(Mockito.anyString())).thenReturn(false);
        ServicioEliminarVehiculo servicioEliminarVehiculo = new ServicioEliminarVehiculo(repositorioVehiculo);
        // act - assert
        BasePrueba.assertThrows(() -> servicioEliminarVehiculo.ejecutar(vehiculo.getPlaca()), ExcepcionDuplicidad.class,"El vehciulo no existe en el sistema");
    }
}
