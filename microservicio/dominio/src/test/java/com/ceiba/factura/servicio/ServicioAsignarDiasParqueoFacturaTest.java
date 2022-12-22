package com.ceiba.factura.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionSobrepasaAnio;
import com.ceiba.factura.modelo.CalculoValorParqueadero;
import com.ceiba.factura.modelo.entidad.Factura;
import com.ceiba.factura.puerto.dao.DaoFactura;
import com.ceiba.factura.puerto.repositorio.RepositorioFactura;
import com.ceiba.factura.servicio.testdatabuilder.FacturaTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ServicioAsignarDiasParqueoFacturaTest {

    private final DaoFactura daoFactura = Mockito.mock(DaoFactura.class);

    @Test
    @DisplayName("Deberia validar la existencia previa de la factura")
    void deberiaValidarLaExistenciaPreviaDeLaFactura() {
        // arrange
        Factura factura = new FacturaTestDataBuilder().conPlacaFK("HKZ123").build();
        RepositorioFactura repositorioFactura = Mockito.mock(RepositorioFactura.class);
        Mockito.when(repositorioFactura.existe(Mockito.anyString())).thenReturn(false);

        ServicioAsignarDiasParqueoFactura servicioAsignarDiasParqueoFactura = new ServicioAsignarDiasParqueoFactura(repositorioFactura,daoFactura);
        // act - assert
        BasePrueba.assertThrows(() -> servicioAsignarDiasParqueoFactura.ejecutar(factura), ExcepcionDuplicidad.class, "El vehciulo no esta estacionado en el parqueadero");
    }

    @Test
    @DisplayName("Deberia actualizar para asignar dias de paruqueo correctamente en el repositorio")
    void deberiaActualizarParaAsignarDiasParqueoCorrectamenteEnElRepositorio() {
        // arrange
        Factura factura = new FacturaTestDataBuilder().conPlacaFK("HKZ123").conCantidadDias(4).build();
        RepositorioFactura repositorioFactura = Mockito.mock(RepositorioFactura.class);
        Mockito.when(repositorioFactura.existe(Mockito.anyString())).thenReturn(true);
        ServicioAsignarDiasParqueoFactura servicioAsignarDiasParqueoFactura = new ServicioAsignarDiasParqueoFactura(repositorioFactura,daoFactura);
        // act
        servicioAsignarDiasParqueoFactura.ejecutar(factura);
        //assert
        Mockito.verify(repositorioFactura, Mockito.times(1)).actualizar(factura);
    }

    @Test
    @DisplayName("Deberia no actualizar para asignar dias de paruqueo correctamente en el repositorio")
    void deberiaNoActualizarParaAsignarDiasParqueoPorSobrepasarCantidadMaxima() {
        // arrange
        Factura factura = new FacturaTestDataBuilder().conPlacaFK("HKZ123").conCantidadDias(400).conFechaIngreso("2022-04-20").build();
        RepositorioFactura repositorioFactura = Mockito.mock(RepositorioFactura.class);
        Mockito.when(repositorioFactura.existe(Mockito.anyString())).thenReturn(true);
        ServicioAsignarDiasParqueoFactura servicioAsignarDiasParqueoFactura = new ServicioAsignarDiasParqueoFactura(repositorioFactura,daoFactura);
        // act - assert
        BasePrueba.assertThrows(() -> servicioAsignarDiasParqueoFactura.ejecutar(factura), ExcepcionSobrepasaAnio.class, "Sobrepasa el anio permitido");
    }

    @Test
    @DisplayName("Deberia no actualizar para asignar dias de paruqueo correctamente en el repositorio")
    void deberiaNoActualizarParaAsignarDiasParqueoPordsSobrepasarCantidadMaxima() {
        // arrange
        Factura factura = new FacturaTestDataBuilder().conPlacaFK("HKZ123").conCantidadDias(400).conFechaIngreso("2022-04-20").build();
        RepositorioFactura repositorioFactura = Mockito.mock(RepositorioFactura.class);
        Mockito.when(repositorioFactura.existe(Mockito.anyString())).thenReturn(true);
        ServicioAsignarDiasParqueoFactura servicioAsignarDiasParqueoFactura = new ServicioAsignarDiasParqueoFactura(repositorioFactura,daoFactura);
        // act - assert
        BasePrueba.assertThrows(() -> servicioAsignarDiasParqueoFactura.ejecutar(factura), ExcepcionSobrepasaAnio.class, "Sobrepasa el anio permitido");
    }

    @Test
    @DisplayName("Deberia generar el total a pagar sin descuento basandose en los 6 dias")
    void deberiaGenerarElTotalApagarSinDescuentoBasandoseEnLosDias() {
        //arrange
        Factura factura = new FacturaTestDataBuilder().build();
        String fecha = "2022-04-12";
        CalculoValorParqueadero calculoValorParqueadero = new CalculoValorParqueadero(fecha);
        //act
        double valor = calculoValorParqueadero.obtenerValorPagoPorDiasParqueo(factura.getCantDias());
        //assert
        assertEquals(valor, factura.getValor());
    }

    @Test
    @DisplayName("Deberia generar el total a pagar sin descuento basandose en 10 dias")
    void deberiaGenerarElTotalApagarConDescuentoBasandoseEnLosDias() {
        //arrange
        Factura factura = new FacturaTestDataBuilder().conCantidadDias(10).conValor(86400).build();
        String fecha = "2022-04-12";
        CalculoValorParqueadero calculoValorParqueadero = new CalculoValorParqueadero(fecha);
        //act
        double valor = calculoValorParqueadero.obtenerValorPagoPorDiasParqueo(factura.getCantDias());
        //assert
        assertEquals(valor, factura.getValor());
    }

    @Test
    @DisplayName("Deberia generar el total a pagar contando solo dos dias siendo estos fines de semana sin ser festivos")
    void deberiaGenerarElTotalApagarContandoSoloDosDiasSiendoEstosFinesDeSemanaSinSerFestivos() {
        //arrange
        Factura factura = new FacturaTestDataBuilder().conCantidadDias(2).conValor(32000).build();
        String fecha = "2022-04-16";
        CalculoValorParqueadero calculoValorParqueadero = new CalculoValorParqueadero(fecha);
        //act
        double valor = calculoValorParqueadero.obtenerValorPagoPorDiasParqueo(factura.getCantDias());
        //assert
        assertEquals(valor, factura.getValor());
    }

    @Test
    @DisplayName("Deberia generar el total a pagar contando solo dos dias entre semana siendo estos festivos")
    void deberiaGenerarElTotalApagarContandoSoloDosDiasEntreSemanaSiendoEstosFestivos() {
        //arrange
        Factura factura = new FacturaTestDataBuilder().conCantidadDias(2).conValor(40000).build();
        String fecha = "2022-04-14";
        CalculoValorParqueadero calculoValorParqueadero = new CalculoValorParqueadero(fecha);
        //act
        double valor = calculoValorParqueadero.obtenerValorPagoPorDiasParqueo(factura.getCantDias());
        //assert
        assertEquals(valor, factura.getValor());
    }

    @Test
    @DisplayName("Deberia generar el total a pagar contando solo un dia fin de semana siendo este festivo")
    void deberiaGenerarElTotalApagarContandoSoloUnDiaFinDeSemanaSiendoEsteFestivo() {
        //arrange
        Factura factura = new FacturaTestDataBuilder().conCantidadDias(1).conValor(20000).build();
        String fecha = "2022-05-1";
        CalculoValorParqueadero calculoValorParqueadero = new CalculoValorParqueadero(fecha);
        //act
        double valor = calculoValorParqueadero.obtenerValorPagoPorDiasParqueo(factura.getCantDias());
        //assert
        assertEquals(valor, factura.getValor());
    }

    @Test
    @DisplayName("Deberia generar el total a pagar contando cinco dias entre semana sin ser festivos")
    void deberiaGenerarElTotalApagarContandoCincoDiasEntreSemanaSinSerFestivos() {
        //arrange
        Factura factura = new FacturaTestDataBuilder().conCantidadDias(5).conValor(60000).build();
        String fecha = "2022-02-07";
        CalculoValorParqueadero calculoValorParqueadero = new CalculoValorParqueadero(fecha);
        //act
        double valor = calculoValorParqueadero.obtenerValorPagoPorDiasParqueo(factura.getCantDias());
        //assert
        assertEquals(valor, factura.getValor());
    }


    @Test
   @DisplayName("Deberia generar el total a pagar contando cinco dias entre semana sin ser festivos")
    void deberiaNoActualizarPorExcesoDeDiasConRelacionAñAnio() {
        //arrange
        Factura factura = new FacturaTestDataBuilder().conCantidadDias(400).conValor(60000).build();
        String fecha = "2022-02-07";
        CalculoValorParqueadero calculoValorParqueadero = new CalculoValorParqueadero(fecha);
        //act
        double valor = calculoValorParqueadero.obtenerValorPagoPorDiasParqueo(factura.getCantDias());
        //assert
        assertEquals(valor, -1);
    }


    @Test
    @DisplayName("Deberia actualizar para asignar dias de paruqueo correctamente en el repositorio")
    void deberiaActualizarParaAsignadrDiasParqueoCorrectamenteEnElRepositorio() {
        // arrange
        Factura factura = new FacturaTestDataBuilder().conPlacaFK("HKZ123").conCantidadDias(365).build();
        RepositorioFactura repositorioFactura = Mockito.mock(RepositorioFactura.class);
        Mockito.when(repositorioFactura.existeVehiculo(Mockito.anyString())).thenReturn(false);

        ServicioAsignarDiasParqueoFactura servicioAsignarDiasParqueoFactura = new ServicioAsignarDiasParqueoFactura(repositorioFactura,daoFactura);
        // act - assert
        BasePrueba.assertThrows(() -> servicioAsignarDiasParqueoFactura.ejecutar(factura), ExcepcionDuplicidad.class, "El vehciulo no esta estacionado en el parqueadero");
    }


}
