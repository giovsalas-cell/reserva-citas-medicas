package com.consultas.medicas.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.consultas.medicas.modelos.CitaMedica;
import com.consultas.medicas.modelos.Paciente;
import com.consultas.medicas.servicios.Servicio;
import org.springframework.web.bind.annotation.RequestParam;

@RestController

public class controller {

    @Autowired
    Servicio servicio;

    @GetMapping("/")
    public List<CitaMedica> listarCitas() {
        return servicio.listarCitaMedicas();
    }

    @GetMapping("/citas-medicas/reservas-disponibles")
    public List<CitaMedica> listarCitasDisponibles() {
        return servicio.listarCitaMedicasDisponibles();
    }

    @GetMapping("/citas-medicas/cancelar/{idCitaMedica}")
    public List<CitaMedica> cancelarCitaMedica(@PathVariable int idCitaMedica) {
        return servicio.cancelarCitaMedica(idCitaMedica);

    }

    @GetMapping("/citas-medicas/agendar/id-cita-medica/{idCitaMedica}/id-paciente/{idPaciente}")
    public List<CitaMedica> agendarCitaMedica(@PathVariable int idCitaMedica,
            @PathVariable int idPaciente) {
        Paciente paciente = servicio.buscarPacientePorId(idPaciente);

        return servicio.agendarCitaMedica(idCitaMedica, paciente);
    }

}
