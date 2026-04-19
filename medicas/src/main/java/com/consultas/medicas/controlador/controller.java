package com.consultas.medicas.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.consultas.medicas.modelos.CitaMedica;
import com.consultas.medicas.modelos.Paciente;
import com.consultas.medicas.servicios.Servicio;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController

public class controller {

    @Autowired
    Servicio servicio;

    @GetMapping("/")
    public List<CitaMedica> listarCitas() {
        List<CitaMedica> lista = servicio.listarCitaMedicas();
        System.out.println("DEBUG: Cantidad de citas encontradas: " + lista.size());
        return lista;
    }

    @GetMapping("/citas-medicas/reservas-disponibles")
    public List<CitaMedica> listarCitasDisponibles() {
        return servicio.listarCitaMedicasDisponibles();
    }

    @PostMapping("/citas-medicas/crear")
    public CitaMedica crearCitaMedica(@Valid @RequestBody CitaMedica citaMedica) {
        return servicio.creaCitaMedica(citaMedica);
    }

    @PutMapping("/citas-medicas/cancelar/{idCitaMedica}")
    public CitaMedica cancelarCitaMedica(@PathVariable int idCitaMedica) {
        return servicio.cancelarCitaMedica(idCitaMedica);

    }

    @PutMapping("/citas-medicas/agendar/id-cita-medica/{idCitaMedica}/id-paciente/{idPaciente}")
    public CitaMedica agendarCitaMedica(@PathVariable int idCitaMedica,
            @PathVariable int idPaciente) {
        Paciente paciente = servicio.buscarPacientePorId(idPaciente);

        return servicio.agendarCitaMedica(idCitaMedica, paciente);
    }

    @DeleteMapping("/citas-medicas/eliminar/{idCitaMedica}")
    public void eliminarCitaMedica(@PathVariable int idCitaMedica) {
        servicio.eliminarCitaMedica(idCitaMedica);
    }

}
