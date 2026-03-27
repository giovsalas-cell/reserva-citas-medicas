package com.consultas.medicas.modelos;

public class CitaMedica {
    private String fecha;
    private boolean disponibilidad;
    private Medico medico;
    private Paciente paciente;

    public CitaMedica(String fecha, boolean disponibilidad, Medico medico, Paciente paciente) {
        this.fecha = fecha;
        this.disponibilidad = disponibilidad;
        this.medico = medico;
        this.paciente = paciente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
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
