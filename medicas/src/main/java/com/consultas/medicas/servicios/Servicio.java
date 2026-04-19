package com.consultas.medicas.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consultas.medicas.modelos.CitaMedica;
import com.consultas.medicas.modelos.Medico;
import com.consultas.medicas.modelos.Paciente;

import jakarta.persistence.EntityNotFoundException;

@Service
public class Servicio {

    @Autowired
    private CitaMedicaRepository citaMedicaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;
    // FUNCIONES
    // Listar todas las listas

    public List<CitaMedica> listarCitaMedicas() {
        return citaMedicaRepository.findAll();
    }

    // Listar listas medicas disponibles
    /*
     * public List<CitaMedica> listarCitaMedicasDisponibles() {
     * List<CitaMedica> listaCitaMedicasDisponibles = new ArrayList<>();
     * for (CitaMedica citaMedica : listaCitaMedicas) {
     * 
     * if (citaMedica.isDisponibilidad() == true) {
     * 
     * listaCitaMedicasDisponibles.add(citaMedica);
     * 
     * }
     * 
     * }
     * return listaCitaMedicasDisponibles;
     * }
     */

    public List<CitaMedica> listarCitaMedicasDisponibles() {
        return citaMedicaRepository.findByDisponibilidad(1);
    }

    // cancelar Lista medica
    /*
     * public CitaMedica cancelarCitaMedica(int idCitaMedica) {
     * for (CitaMedica citaMedica : listaCitaMedicas) {
     * 
     * if (citaMedica.getIdCitaMedica() == idCitaMedica) {
     * 
     * citaMedica.setDisponibilidad(true);
     * citaMedica.setPaciente(null);
     * return citaMedica;
     * }
     * 
     * }
     * return null;
     * }
     */
    public CitaMedica cancelarCitaMedica(int idCitaMedica) {
        CitaMedica citaMedica = citaMedicaRepository.findById(idCitaMedica).orElse(null);
        if (citaMedica != null) {
            citaMedica.setDisponibilidad(1);
            citaMedica.setPaciente(null);
            citaMedicaRepository.save(citaMedica);
            return citaMedica;
        }
        return null;
    }

    public CitaMedica creaCitaMedica(CitaMedica citaMedica) {
        return citaMedicaRepository.save(citaMedica);
    }

    // agendar cita medica
    public CitaMedica agendarCitaMedica(int id, Paciente paciente) {
        CitaMedica citaMedica = citaMedicaRepository.findById(id).orElse(null);
        if (citaMedica != null) {
            citaMedica.setDisponibilidad(0);
            citaMedica.setPaciente(paciente);
            citaMedicaRepository.save(citaMedica);
            return citaMedica;
        }
        return null;
    }

    public void eliminarCitaMedica(int id) {
        citaMedicaRepository.deleteById(id);
    }

    /*
     * public CitaMedica agendarCitaMedica(int id, Paciente paciente) {
     * 
     * for (CitaMedica citaMedica : listaCitaMedicas) {
     * if (citaMedica.getIdCitaMedica() == id && citaMedica.isDisponibilidad() ==
     * true && paciente != null) {
     * citaMedica.setDisponibilidad(false);
     * citaMedica.setPaciente(paciente);
     * return citaMedica;
     * }
     * }
     * return null;
     * }
     */

    // buscar Paciente Por Id
    public Paciente buscarPacientePorId(int idPaciente) {

        return pacienteRepository.findById(idPaciente)
                .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado"));

    }

}
