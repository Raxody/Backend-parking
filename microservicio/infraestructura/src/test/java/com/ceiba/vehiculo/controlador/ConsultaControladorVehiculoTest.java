package com.ceiba.vehiculo.controlador;

import com.ceiba.ApplicationMock;
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
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ConsultaControladorVehiculo.class)
@ContextConfiguration(classes= ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ConsultaControladorVehiculoTest {

    @Autowired
    private MockMvc mocMvc;

    @Test
    @DisplayName("Deberia listar los vehiculos em memoria")
    void deberiaListarVehiculos() throws Exception {
        // arrange
        // act - assert
        mocMvc.perform(get("/listarVehiculo")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].placa", is("HHHV18G")))
                .andExpect(jsonPath("$[0].idPropietario", is(1004930511)))
                .andExpect(jsonPath("$[0].telefonoContacto", is(163565165)))
                .andExpect(jsonPath("$[1].placa", is("HHH174")))
                .andExpect(jsonPath("$[1].idPropietario", is(1004930512)))
                .andExpect(jsonPath("$[1].telefonoContacto", is(163565162)));
    }


}
