package com.consultas.medicas.modelos;

public class Medico {
    private int idMedico;
    private String rutMedico;
    private String nombreMedico;
    private String especialidad;

    public Medico(int idMedico, String rutMedico, String nombreMedico, String especialidad) {
        this.idMedico = idMedico;
        this.rutMedico = rutMedico;
        this.nombreMedico = nombreMedico;
        this.especialidad = especialidad;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public String getRutMedico() {
        return rutMedico;
    }

    public void setRutMedico(String rutMedico) {
        this.rutMedico = rutMedico;
    }

    public String getNombreMedico() {
        return nombreMedico;
    }

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

}
