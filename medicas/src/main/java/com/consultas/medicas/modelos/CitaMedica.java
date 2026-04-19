package com.consultas.medicas.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Table(name = "CITA_MEDICA")
@Entity
public class CitaMedica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CITA_MEDICA")
    private int idCitaMedica;
    @NotBlank(message = "fecha de cita obligatoria")
    @Column(name = "FECHA")
    private String fecha;
    @NotNull(message = "disponibilidad de cita obligatoria")
    @Column(name = "DISPONIBILIDAD")
    private int disponibilidad;
    @ManyToOne
    @JoinColumn(name = "ID_MEDICO")
    @NotNull(message = "debe haber un medico en la cita medica")
    private Medico medico;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_PACIENTE", nullable = true)
    private Paciente paciente;

    public CitaMedica() {
    }

    public CitaMedica(int idCitaMedica, String fecha, int disponibilidad, Medico medico) {
        this.idCitaMedica = idCitaMedica;
        this.fecha = fecha;
        this.disponibilidad = disponibilidad;
        this.medico = medico;
        this.paciente = null;
    }

    public CitaMedica(int idCitaMedica, String fecha, int disponibilidad, Medico medico, Paciente paciente) {
        this.idCitaMedica = idCitaMedica;
        this.fecha = fecha;
        this.disponibilidad = disponibilidad;
        this.medico = medico;
        this.paciente = paciente;
    }

    public int getIdCitaMedica() {
        return idCitaMedica;
    }

    public void setIdCitaMedica(int idCitaMedica) {
        this.idCitaMedica = idCitaMedica;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(int disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

}
