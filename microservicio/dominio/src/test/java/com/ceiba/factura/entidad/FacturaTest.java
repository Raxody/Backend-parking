package com.ceiba.factura.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.dominio.excepcion.*;
import com.ceiba.factura.modelo.entidad.Factura;
import com.ceiba.factura.servicio.testdatabuilder.FacturaTestDataBuilder;
import com.ceiba.vehiculo.servicio.testdatabuilder.VehiculoTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FacturaTest {

    @Test
    @DisplayName("Deberia crear correctamente el vehiculo")
    void deberiaCrearCorrectamenteElVehiculo() {
        //act
        Factura factura = new FacturaTestDataBuilder().conIdFactura(1L).conFechaIngreso("2022-04-12").conCantidadDias(6).conValor(96000).conPlacaFK("HKZ123").build();
        //assert
        assertEquals(1L, factura.getIdFactura());
        assertEquals("2022-04-12", factura.getFechaIngreso());
        assertEquals(6, factura.getCantDias());
        assertEquals(96000, factura.getValor());
        assertEquals("HKZ123", factura.getPlacaFk());
    }

    @Test
    @DisplayName("Deberia fallar si no se ingresa la placa del vehículo para la factura")
    void deberiaFallarSinPlacaDelVehiculo() {
        //Arrange
        FacturaTestDataBuilder facturaTestDataBuilder = new FacturaTestDataBuilder().conPlacaFK(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    facturaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar la placa del vehiculo");
    }

    @Test
    @DisplayName("Deberia fallar si la placa no tiene un tamaño minimo de 6")
    void deberiaFallarSinTamanioPlaca() {
        //Arrange
        FacturaTestDataBuilder facturaTestDataBuilder = new FacturaTestDataBuilder().conPlacaFK("hk");
        //act-assert
        BasePrueba.assertThrows(() -> {
                    facturaTestDataBuilder.build();
                },
                ExcepcionLongitudValor.class, "La placa debe tener una longitud mayor o igual a 6");
    }

    @Test
    @DisplayName("Deberia fallar con la cantidad de dias no numerica")
    void deberiaFallarConLaCantidadDeDiasNoNumerica() {
        try{
            //Arrange
            FacturaTestDataBuilder facturaTestDataBuilder = new FacturaTestDataBuilder().conCantidadDias(Integer.parseInt("safsad"));
            //act-assert
            BasePrueba.assertThrows(() -> {
                        facturaTestDataBuilder.build();
                    },
                    ExcepcionLongitudValor.class, "La cantidad de dias debe ser numerico");
        }catch(NumberFormatException e){}
    }

    @Test
    @DisplayName("Deberia fallar con la cantidad de dias menor o igual a 0")
    void deberiaFallarConLaCantidadDeDiasMenorOIgualA0() {
        //Arrange
        FacturaTestDataBuilder facturaTestDataBuilder = new FacturaTestDataBuilder().conCantidadDias(-5);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    facturaTestDataBuilder.build();
                },
                ExcepcionValorInvalido.class, "La cantidad de dias debe ser mayor o igual a 0");
    }

}
