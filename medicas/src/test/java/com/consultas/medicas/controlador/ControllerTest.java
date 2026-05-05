package com.consultas.medicas.controlador;

import com.consultas.medicas.modelos.CitaMedica;
import com.consultas.medicas.modelos.Medico;
import com.consultas.medicas.modelos.Paciente;
import com.consultas.medicas.servicios.Servicio;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controller.class)
class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Servicio servicio;

    @Autowired
    private ObjectMapper objectMapper;

    private Medico medicoEjemplo;
    private CitaMedica citaEjemplo;
    private Paciente pacienteEjemplo;

    @BeforeEach
    void setUp() {
        medicoEjemplo = new Medico(1, "12345678-9", "Dr. Juan Perez", "Cardiologia");
        pacienteEjemplo = new Paciente(1, "98765432-1", "Maria Lopez",
                987654321, "Av. Principal 123", null);
        citaEjemplo = new CitaMedica(1, "2026-06-01", 1, medicoEjemplo);
    }

    @Test
    void listarCitas_retorna200() throws Exception {
        when(servicio.listarCitaMedicas()).thenReturn(Arrays.asList(citaEjemplo));

        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST /citas-medicas/crear - crea cita y retorna 200")
    void crearCitaMedica_retorna200() throws Exception {
        when(servicio.creaCitaMedica(any(CitaMedica.class))).thenReturn(citaEjemplo);

        mockMvc.perform(post("/citas-medicas/crear")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(citaEjemplo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fecha").value("2026-06-01"));
    }

    @Test
    @DisplayName("PUT /citas-medicas/cancelar/{id} - cancela cita correctamente")
    void cancelarCitaMedica_retorna200() throws Exception {
        when(servicio.cancelarCitaMedica(1)).thenReturn(citaEjemplo);

        mockMvc.perform(put("/citas-medicas/cancelar/1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("DELETE /citas-medicas/eliminar/{id} - elimina correctamente")
    void eliminarCitaMedica_retorna200() throws Exception {
        doNothing().when(servicio).eliminarCitaMedica(1);
        when(servicio.listarCitaMedicas()).thenReturn(Arrays.asList(citaEjemplo));

        mockMvc.perform(delete("/citas-medicas/eliminar/1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /citas-medicas/reservas-disponibles - retorna citas disponibles")
    void listarCitasDisponibles_retorna200() throws Exception {
        when(servicio.listarCitaMedicasDisponibles()).thenReturn(Arrays.asList(citaEjemplo));

        mockMvc.perform(get("/citas-medicas/reservas-disponibles"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("PUT /citas-medicas/agendar - agenda cita con paciente")
    void agendarCitaMedica_retorna200() throws Exception {
        when(servicio.buscarPacientePorId(1)).thenReturn(pacienteEjemplo);
        when(servicio.agendarCitaMedica(1, pacienteEjemplo)).thenReturn(citaEjemplo);

        mockMvc.perform(put("/citas-medicas/agendar/id-cita-medica/1/id-paciente/1"))
                .andExpect(status().isOk());
    }
}