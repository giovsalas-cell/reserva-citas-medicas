package com.consultas.medicas.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.consultas.medicas.modelos.CitaMedica;
import com.consultas.medicas.modelos.Medico;
import com.consultas.medicas.modelos.Paciente;

@Service
public class Servicio {

    public List<CitaMedica> listaCitaMedicas = new ArrayList<>();
    public List<Paciente> listaPacientes = new ArrayList<>();

    public Servicio() {

        Paciente p1 = new Paciente(1, "17807175-0", "Giovanni Salas", 111111111, "Direccion1");
        Paciente p2 = new Paciente(2, "11111111-9", "Pedro Pascal", 222222222, "Direccion2");
        Paciente p3 = new Paciente(3, "3333333-3", "Adrian Tepes", 333333333, "Direccion3");
        Paciente p4 = new Paciente(4, "4444444-4", "Sarah Connor", 444444444, "Direccion4");
        Paciente p5 = new Paciente(5, "5555555-5", "Bruce Wayne", 555555555, "Direccion5");
        Paciente p6 = new Paciente(6, "6666666-6", "Ellen Ripley", 666666666, "Direccion6");
        Paciente p7 = new Paciente(7, "7777777-7", "Tony Stark", 777777777, "Direccion7");
        Paciente p8 = new Paciente(8, "8888888-8", "Marty McFly", 888888888, "Direccion8");

        Medico m1 = new Medico(1, "4444444-4", "Doctor Chapatin", "Medicina General");
        Medico m2 = new Medico(2, "5555555-5", "Gregory House", "Medicina de Lupus");
        Medico m3 = new Medico(3, "6666666-6", "Doctor Faustus", "Cirugía General");
        Medico m4 = new Medico(4, "7777777-7", "Stephen Strange", "Neurocirugía");
        Medico m5 = new Medico(5, "8888888-8", "Dana Scully", "Patología");
        Medico m6 = new Medico(6, "9999999-9", "Leonard McCoy", "Medicina Espacial");
        Medico m7 = new Medico(7, "1010101-0", "Hannibal Lecter", "Psiquiatría");
        Medico m8 = new Medico(8, "1212121-2", "Julius Hibbert", "Pediatría");

        CitaMedica cita1 = new CitaMedica(1, "2026-05-09 12:00:00", true, m1);
        CitaMedica cita2 = new CitaMedica(2, "2026-05-09 13:00:00", false, m2, p2);
        CitaMedica cita3 = new CitaMedica(3, "2026-05-09 14:00:00", false, m3, p1);
        CitaMedica cita4 = new CitaMedica(4, "2026-05-10 09:00:00", true, m1);
        CitaMedica cita5 = new CitaMedica(5, "2026-05-10 10:00:00", true, m2);
        CitaMedica cita6 = new CitaMedica(6, "2026-05-10 11:00:00", true, m3);

        CitaMedica cita7 = new CitaMedica(7, "2026-05-11 09:00:00", true, m4);
        CitaMedica cita8 = new CitaMedica(8, "2026-05-11 10:00:00", true, m5);
        CitaMedica cita9 = new CitaMedica(9, "2026-05-11 11:00:00", false, m6, p4);
        CitaMedica cita10 = new CitaMedica(10, "2026-05-12 15:00:00", true, m7);
        CitaMedica cita11 = new CitaMedica(11, "2026-05-12 16:00:00", true, m8);
        CitaMedica cita12 = new CitaMedica(12, "2026-05-12 17:00:00", false, m4, p5);

        listaPacientes.add(p1);
        listaPacientes.add(p2);
        listaPacientes.add(p3);
        listaPacientes.add(p4);
        listaPacientes.add(p5);
        listaPacientes.add(p6);
        listaPacientes.add(p7);
        listaPacientes.add(p8);

        listaCitaMedicas.add(cita1);
        listaCitaMedicas.add(cita2);
        listaCitaMedicas.add(cita3);
        listaCitaMedicas.add(cita4);
        listaCitaMedicas.add(cita5);
        listaCitaMedicas.add(cita6);
        listaCitaMedicas.add(cita7);
        listaCitaMedicas.add(cita8);
        listaCitaMedicas.add(cita9);
        listaCitaMedicas.add(cita10);
        listaCitaMedicas.add(cita11);
        listaCitaMedicas.add(cita12);

    }

    // FUNCIONES
    // Listar todas las listas

    public List<CitaMedica> listarCitaMedicas() {
        return listaCitaMedicas;
    }

    // Listar listas medicas disponibles

    public List<CitaMedica> listarCitaMedicasDisponibles() {
        List<CitaMedica> listaCitaMedicasDisponibles = new ArrayList<>();
        for (CitaMedica citaMedica : listaCitaMedicas) {

            if (citaMedica.isDisponibilidad() == true) {

                listaCitaMedicasDisponibles.add(citaMedica);

            }

        }
        return listaCitaMedicasDisponibles;
    }

    // cancelar Lista medica

    public List<CitaMedica> cancelarCitaMedica(int idCitaMedica) {
        for (CitaMedica citaMedica : listaCitaMedicas) {

            if (citaMedica.getIdCitaMedica() == idCitaMedica) {

                citaMedica.setDisponibilidad(true);
                citaMedica.setPaciente(null);

            }

        }
        return listaCitaMedicas;
    }

    // buscar Paciente Por Id
    public Paciente buscarPacientePorId(int idPaciente) {

        for (Paciente pacientec : listaPacientes) {
            if (pacientec.getIdPaciente() == idPaciente) {
                return pacientec;
            }
        }
        return null;
    }
    // agendar cita medica

    public List<CitaMedica> agendarCitaMedica(int id, Paciente paciente) {

        for (CitaMedica citaMedica : listaCitaMedicas) {
            if (citaMedica.getIdCitaMedica() == id && citaMedica.isDisponibilidad() == true && paciente != null) {
                citaMedica.setDisponibilidad(false);
                citaMedica.setPaciente(paciente);
            }
        }
        return listaCitaMedicas;
    }

}
