package com.consultas.medicas.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.consultas.medicas.modelos.CitaMedica;
import com.consultas.medicas.modelos.Paciente;
import com.consultas.medicas.servicios.Servicio;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class controller {

    @Autowired
    Servicio servicio;

    @GetMapping("/")
    public CollectionModel<EntityModel<CitaMedica>> listarCitas() {
        List<EntityModel<CitaMedica>> citas = servicio.listarCitaMedicas().stream()
                .map(cita -> EntityModel.of(cita,
                        linkTo(methodOn(controller.class).listarCitas()).withSelfRel()))
                .toList();
        return CollectionModel.of(citas,
                linkTo(methodOn(controller.class).listarCitas()).withSelfRel());
    }

    @GetMapping("/citas-medicas/reservas-disponibles")
    public CollectionModel<EntityModel<CitaMedica>> listarCitasDisponibles() {
        List<EntityModel<CitaMedica>> citas = servicio.listarCitaMedicasDisponibles().stream()
                .map(cita -> EntityModel.of(cita,
                        linkTo(methodOn(controller.class).listarCitasDisponibles()).withSelfRel(),
                        linkTo(methodOn(controller.class).listarCitas()).withRel("todas")))
                .toList();
        return CollectionModel.of(citas,
                linkTo(methodOn(controller.class).listarCitasDisponibles()).withSelfRel());
    }

    @PostMapping("/citas-medicas/crear")
    public EntityModel<CitaMedica> crearCitaMedica(@Valid @RequestBody CitaMedica citaMedica) {
        CitaMedica nueva = servicio.creaCitaMedica(citaMedica);
        return EntityModel.of(nueva,
                linkTo(methodOn(controller.class).listarCitas()).withRel("todas"),
                linkTo(methodOn(controller.class).listarCitasDisponibles()).withRel("disponibles"));
    }

    @PutMapping("/citas-medicas/cancelar/{idCitaMedica}")
    public EntityModel<CitaMedica> cancelarCitaMedica(@PathVariable int idCitaMedica) {
        CitaMedica cita = servicio.cancelarCitaMedica(idCitaMedica);
        return EntityModel.of(cita,
                linkTo(methodOn(controller.class).listarCitas()).withRel("todas"),
                linkTo(methodOn(controller.class).listarCitasDisponibles()).withRel("disponibles"));
    }

    @PutMapping("/citas-medicas/agendar/id-cita-medica/{idCitaMedica}/id-paciente/{idPaciente}")
    public EntityModel<CitaMedica> agendarCitaMedica(@PathVariable int idCitaMedica,
            @PathVariable int idPaciente) {
        Paciente paciente = servicio.buscarPacientePorId(idPaciente);
        CitaMedica cita = servicio.agendarCitaMedica(idCitaMedica, paciente);
        return EntityModel.of(cita,
                linkTo(methodOn(controller.class).listarCitas()).withRel("todas"),
                linkTo(methodOn(controller.class).listarCitasDisponibles()).withRel("disponibles"));
    }

    @DeleteMapping("/citas-medicas/eliminar/{idCitaMedica}")
    public void eliminarCitaMedica(@PathVariable int idCitaMedica) {
        servicio.eliminarCitaMedica(idCitaMedica);
    }
}