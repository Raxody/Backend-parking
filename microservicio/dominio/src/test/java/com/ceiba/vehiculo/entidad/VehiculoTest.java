package com.ceiba.vehiculo.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.*;
import com.ceiba.vehiculo.modelo.entidad.Vehiculo;
import com.ceiba.vehiculo.servicio.testdatabuilder.VehiculoTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VehiculoTest {

    @Test
    @DisplayName("Deberia crear correctamente el vehiculo")
    void deberiaCrearCorrectamenteElUsusuario() {
        //act
        Vehiculo vehiculo = new VehiculoTestDataBuilder().conPlaca("HKZ123").conIdPropietario(1000493015L).conTelefonoContacto(3152546967L).build();
        //assert
        assertEquals("HKZ123", vehiculo.getPlaca());
        assertEquals(1000493015L, vehiculo.getIdPropietario());
        assertEquals(3152546967L, vehiculo.getTelefonoContacto());
    }

    @Test
    @DisplayName("Deberia fallar si no se ingresa la placa del vehículo")
    void deberiaFallarSinPlacaDelVehiculo() {

        //Arrange
        VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder().conPlaca(null).conIdPropietario(1000493015L).conTelefonoContacto(3152546967L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    vehiculoTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar la placa del vehiculo");
    }

    @Test
    @DisplayName("Deberia fallar si no se ingresa el id del propietario del vehículo")
    void deberiaFallarSinIdPropietario() {

        //Arrange
        VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder().conPlaca("HKZ123").conIdPropietario(null).conTelefonoContacto(3152546967L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    vehiculoTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el id del propietario");
    }

    @Test
    @DisplayName("Deberia fallar si no se ingresa el teléfono de contacto del propietario")
    void deberiaFallarTelefonoContacto() {
        //Arrange
        VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder().conPlaca("HKZ123").conIdPropietario(1000493015L).conTelefonoContacto(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    vehiculoTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el teléfono de contacto del propietario");
    }


    @Test
    @DisplayName("Deberia fallar si la placa no tiene un tamaño minimo de 6")
    void deberiaFallarSinTamanioPlaca() {

        //Arrange
        VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder().conPlaca("HK").conIdPropietario(1000493015L).conTelefonoContacto(3152546967L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    vehiculoTestDataBuilder.build();
                },
                ExcepcionLongitudValor.class, "La placa debe tener una longitud mayor o igual a 6");
    }

    @Test
    @DisplayName("Deberia fallar si el teléfono de contacto no tiene un tamaño minimo de 10")
    void deberiaFallarSinTamanioTelefonoContacto() {

        //Arrange
        VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder().conPlaca("HKZ123").conIdPropietario(1000493015L).conTelefonoContacto(3157L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    vehiculoTestDataBuilder.build();
                },
                ExcepcionLongitudValor.class, "el teléfono de contacto debe tener una longitud mayor o igual a 10");
    }

    @Test
    @DisplayName("Deberia fallar si en el campo telefono cliente se ingresan letras")
    void deberiaFallarConLetrasEnElCampoTelefonoContacto() {

        try{
            //Arrange
            VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder().conPlaca("HKZ123").conIdPropietario(Long.parseLong("sfsdsfd")).conTelefonoContacto(3157L);
            //act-assert
            BasePrueba.assertThrows(() -> {
                        vehiculoTestDataBuilder.build();
                    },
                    ExcepcionLongitudValor.class, "El teléfono de contacto debe ser numerico");
        }catch(NumberFormatException e){}
    }

    @Test
    @DisplayName("Deberia fallar si en el campo id propietario se ingresan letras")
    void deberiaFallarConLetrasEnElCampoIdPropietario() {

        try{
            //Arrange
            VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder().conPlaca("HKZ123").conIdPropietario(1000493015L).conTelefonoContacto(Long.parseLong("sfsdsfd"));
            //act-assert
            BasePrueba.assertThrows(() -> {
                        vehiculoTestDataBuilder.build();
                    },
                    ExcepcionLongitudValor.class, "La identificación del propietario debe ser numerico");
        }catch(NumberFormatException e){}
    }




}
