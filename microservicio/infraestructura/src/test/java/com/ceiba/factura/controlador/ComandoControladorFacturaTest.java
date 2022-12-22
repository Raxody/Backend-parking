package com.ceiba.factura.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.factura.ComandoFacturaAsignarDias;
import com.ceiba.factura.modelo.CalculoValorParqueadero;
import com.ceiba.factura.modelo.dto.DtoFacturaAsignarDias;
import com.ceiba.factura.puerto.dao.DaoFactura;
import com.ceiba.factura.servicio.testdatabuilder.ComandoFacturaTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ComandoControladorFactura.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ComandoControladorFacturaTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Autowired
    private DaoFactura daoFactura;

    @Test
    @DisplayName("Deberia validar que la factura no exista para crarla y luego mostrar que este se creo")
    void ITdeberiaValidarQueNoExisteParaCrearUnaFacturaYLuegoMostrarQueEsteSeCreo() throws Exception {
        // arrange
        String placa = "HHHV18G";
        ComandoFacturaAsignarDias comandoFacturaAsignarDias = new ComandoFacturaTestDataBuilder().conPlaca(placa).conIdFactura(2L).build();
        int tamanioArray = daoFactura.listar().size();
        // act - assert
        //valido no existencia
        assertEquals(false, this.existeFactura(placa));

        //agrego
        mocMvc.perform(post("/facturaVariado/agregar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoFacturaAsignarDias)))
                .andExpect(status().isOk())
                .andExpect(content().json("{'valor': 2}"));

        //Demuestro que se creo
        assertEquals(daoFactura.listar().get(tamanioArray).getPlacaFk(), comandoFacturaAsignarDias.getPlacaFk());
        assertEquals(daoFactura.listar().get(tamanioArray).getFechaIngreso(), comandoFacturaAsignarDias.getFechaIngreso());
        assertEquals(daoFactura.listar().get(tamanioArray).getIdFactura(), comandoFacturaAsignarDias.getIdFactura());
    }

    private boolean existeFactura(String placa){
        for (DtoFacturaAsignarDias factura : daoFactura.listar()) {
            if (factura.getPlacaFk().equals(placa)
                    && factura.getCantDias() == 0) {
                return true;
            }
        }
        return false;
    }

    @Test
    @DisplayName("Deberia verificar la existencia de la factura para luego actualizarla y realizar los respectivos calculos del valor a pagar")
    void ITdeberiaVerificarLaExistenciaDelVehiculoLuegoActualizarloCalcularLosDatosYMostrarLosNuevosDatos() throws Exception {
        // arrange
        ComandoFacturaAsignarDias comandoFacturaAsignarDias = new ComandoFacturaTestDataBuilder().conDias(5).conPlaca("HHH174").build();
        CalculoValorParqueadero calculoValorParqueadero = new CalculoValorParqueadero( comandoFacturaAsignarDias.getFechaIngreso());
        // act - assert
        //Valido existencia
        assertEquals(true, this.existeFactura(comandoFacturaAsignarDias.getPlacaFk()));

        ITvalidarAssertsFacturaParaTestActualizarYCalcular(comandoFacturaAsignarDias.getPlacaFk(),
                comandoFacturaAsignarDias.getIdFactura(), comandoFacturaAsignarDias.getFechaIngreso());

        double valor = calculoValorParqueadero.obtenerValorPagoPorDiasParqueo(comandoFacturaAsignarDias.getCantDias());
        //actualizo
        mocMvc.perform(put("/facturaVariado/calcular/{placa}", comandoFacturaAsignarDias.getPlacaFk())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoFacturaAsignarDias)))
                .andExpect(status().isOk());

        //Demuestro que se creo
        ITvalidarAssertsFacturaParaTestActualizarYCalcular(comandoFacturaAsignarDias.getPlacaFk(),
                comandoFacturaAsignarDias.getIdFactura(), comandoFacturaAsignarDias.getFechaIngreso());
        assertEquals(daoFactura.listar().get(0).getCantDias(), comandoFacturaAsignarDias.getCantDias());
        assertEquals(daoFactura.listar().get(0).getValor(),valor);
    }

    private void ITvalidarAssertsFacturaParaTestActualizarYCalcular(String placa,Long id, String fechaIngreso){
        assertEquals(daoFactura.listar().get(0).getPlacaFk(),placa);
        assertEquals(daoFactura.listar().get(0).getIdFactura(),id);
        assertEquals(daoFactura.listar().get(0).getFechaIngreso(),fechaIngreso);
    }




}
