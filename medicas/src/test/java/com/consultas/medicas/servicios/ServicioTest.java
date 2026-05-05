package com.consultas.medicas.servicios;

import com.consultas.medicas.modelos.CitaMedica;
import com.consultas.medicas.modelos.Medico;
import com.consultas.medicas.modelos.Paciente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServicioTest {

    @Mock
    private CitaMedicaRepository citaMedicaRepository;
    @Mock
    private MedicoRepository medicoRepository;
    @Mock
    private PacienteRepository pacienteRepository;

    @InjectMocks
    private Servicio servicio;

    private Medico medicoEjemplo;
    private Paciente pacienteEjemplo;
    private CitaMedica citaEjemplo;

    @BeforeEach
    void setUp() {
        medicoEjemplo = new Medico(1, "12345678-9", "Dr. Juan Perez", "Cardiologia");

        pacienteEjemplo = new Paciente(1, "98765432-1", "Maria Lopez",
                987654321, "Av. Principal 123", null);

        citaEjemplo = new CitaMedica(1, "2026-06-01", 1, medicoEjemplo);
    }

    @Test
    @DisplayName("listarCitaMedicas - retorna lista de citas")
    void listarCitaMedicas_retornaLista() {
        when(citaMedicaRepository.findAll()).thenReturn(Arrays.asList(citaEjemplo));

        List<CitaMedica> resultado = servicio.listarCitaMedicas();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(citaMedicaRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("creaCitaMedica - guarda y retorna la cita")
    void creaCitaMedica_guardaYretornaCita() {
        when(citaMedicaRepository.save(any(CitaMedica.class))).thenReturn(citaEjemplo);

        CitaMedica resultado = servicio.creaCitaMedica(citaEjemplo);

        assertNotNull(resultado);
        assertEquals("2026-06-01", resultado.getFecha());
        assertEquals(1, resultado.getDisponibilidad());
        verify(citaMedicaRepository, times(1)).save(citaEjemplo);
    }

    @Test
    @DisplayName("cancelarCitaMedica - cancela y libera la cita")
    void cancelarCitaMedica_idExiste_cancelaCita() {
        citaEjemplo.setPaciente(pacienteEjemplo);
        when(citaMedicaRepository.findById(1)).thenReturn(Optional.of(citaEjemplo));
        when(citaMedicaRepository.save(any(CitaMedica.class))).thenReturn(citaEjemplo);

        CitaMedica resultado = servicio.cancelarCitaMedica(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getDisponibilidad());
        assertNull(resultado.getPaciente());
    }

    @Test
    @DisplayName("cancelarCitaMedica - retorna null si la cita no existe")
    void cancelarCitaMedica_idNoExiste_retornaNull() {
        when(citaMedicaRepository.findById(99)).thenReturn(Optional.empty());

        CitaMedica resultado = servicio.cancelarCitaMedica(99);

        assertNull(resultado);
    }

    @Test
    @DisplayName("agendarCitaMedica - agenda la cita con paciente")
    void agendarCitaMedica_idExiste_agendaCita() {
        when(citaMedicaRepository.findById(1)).thenReturn(Optional.of(citaEjemplo));
        when(citaMedicaRepository.save(any(CitaMedica.class))).thenReturn(citaEjemplo);

        CitaMedica resultado = servicio.agendarCitaMedica(1, pacienteEjemplo);

        assertNotNull(resultado);
        assertEquals(0, resultado.getDisponibilidad());
        assertEquals(pacienteEjemplo, resultado.getPaciente());
    }

    @Test
    @DisplayName("eliminarCitaMedica - elimina correctamente")
    void eliminarCitaMedica_idExiste_eliminaSinError() {
        doNothing().when(citaMedicaRepository).deleteById(1);

        assertDoesNotThrow(() -> servicio.eliminarCitaMedica(1));
        verify(citaMedicaRepository, times(1)).deleteById(1);
    }

    @Test
    @DisplayName("listarCitaMedicasDisponibles - retorna solo citas disponibles")
    void listarCitaMedicasDisponibles_retornaListaDisponibles() {
        when(citaMedicaRepository.findByDisponibilidad(1)).thenReturn(Arrays.asList(citaEjemplo));

        List<CitaMedica> resultado = servicio.listarCitaMedicasDisponibles();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(1, resultado.get(0).getDisponibilidad());
        verify(citaMedicaRepository, times(1)).findByDisponibilidad(1);
    }

    @Test
    @DisplayName("buscarPacientePorId - retorna paciente cuando existe")
    void buscarPacientePorId_idExiste_retornaPaciente() {
        when(pacienteRepository.findById(1)).thenReturn(Optional.of(pacienteEjemplo));

        Paciente resultado = servicio.buscarPacientePorId(1);

        assertNotNull(resultado);
        assertEquals("Maria Lopez", resultado.getNombrePaciente());
    }
}