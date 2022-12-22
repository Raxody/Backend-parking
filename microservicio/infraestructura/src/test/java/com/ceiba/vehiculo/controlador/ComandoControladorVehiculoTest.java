package com.ceiba.vehiculo.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.vehiculo.comando.ComandoVehiculo;
import com.ceiba.vehiculo.modelo.dto.DtoVehiculo;
import com.ceiba.vehiculo.puerto.dao.DaoVehiculo;
import com.ceiba.vehiculo.servicio.testdatabuilder.ComandoVehiculoTestDataBuilder;
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

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ComandoControladorVehiculo.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ComandoControladorVehiculoTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Autowired
    private DaoVehiculo daoVehiculo;

    @Test
    @DisplayName("Deberia eliminar un vehiculo")
    void deberiaEliminarUnVehiculo() throws Exception {
        // arrange
        String placa = "HHHV18G";
        // act - assert
        mocMvc.perform(delete("/vehiculoVariado/borrar/{placa}", placa)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mocMvc.perform(get("/listarVehiculo")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
        //valido existencia
        assertEquals(false, existeVehiculo(placa));
    }

    private boolean existeVehiculo(String placa){
        for (DtoVehiculo vehiculo : daoVehiculo.listar()) {
            if (vehiculo.getPlaca().equals(placa)) {
                return true;
            }
        }
        return false;
    }

    @Test
    @DisplayName("Deberia validar que el vehiculo no exista para crar un vehiculo nuevo y luego mostrar que este se creo")
    void ITdeberiaValidarQueNoExisteParaCrearUnVehiculoYLuegoMostrarQueEsteSeCreo() throws Exception {
        // arrange
        ComandoVehiculo comandoVehiculo = new ComandoVehiculoTestDataBuilder().build();
        boolean existe = false;
        int tamanioArray = daoVehiculo.listar().size();
        // act - assert
        //valido existencia
        assertEquals(false, existeVehiculo(comandoVehiculo.getPlaca()));
        //agrego
        mocMvc.perform(post("/vehiculoVariado/agregar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoVehiculo)))
                .andExpect(status().isOk())
                .andExpect(content().json("{'valor': 1000493012}"));
        //Demuestro que se creo
        assertEquals(daoVehiculo.listar().get(tamanioArray).getPlaca(), comandoVehiculo.getPlaca());
        assertEquals(daoVehiculo.listar().get(tamanioArray).getIdPropietario(), comandoVehiculo.getIdentificacion());
        assertEquals(daoVehiculo.listar().get(tamanioArray).getTelefonoContacto(), comandoVehiculo.getTelefonoContacto());
    }

    @Test
    @DisplayName("Deberia verificar la existencia del vehiculo luego actualizar y mostrar que este se creo")
    void ITdeberiaVerificarLaExistenciaDelVehiculoLuegoActualizarloYMostrarLosNuevosDatos() throws Exception {
        // arrange
        String placa = "HHHV18G";
        ComandoVehiculo comandoVehiculo = new ComandoVehiculoTestDataBuilder().conPlaca(placa).build();
        // act - assert
        //Consulta que el vehiculo este
        ITvalidarAssertsVehiculoParaTestActualizar(comandoVehiculo.getPlaca(),1004930511L,163565165L);
        //Actualiza el vehiculo
        mocMvc.perform(put("/vehiculoVariado/actualizar/{placa}", placa)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoVehiculo)))
                .andExpect(status().isOk());
        //Valida que se actualizo
        ITvalidarAssertsVehiculoParaTestActualizar(comandoVehiculo.getPlaca(),comandoVehiculo.getIdentificacion(),comandoVehiculo.getTelefonoContacto());
    }

    private void ITvalidarAssertsVehiculoParaTestActualizar(String placa,Long id, Long telefonoContacto){
        assertEquals(daoVehiculo.listar().get(0).getPlaca(),placa);
        assertEquals(daoVehiculo.listar().get(0).getIdPropietario(),id);
        assertEquals(daoVehiculo.listar().get(0).getTelefonoContacto(), telefonoContacto);
    }


}
