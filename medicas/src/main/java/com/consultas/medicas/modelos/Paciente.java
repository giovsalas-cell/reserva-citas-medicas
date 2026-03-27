package com.consultas.medicas.modelos;

public class Paciente {
    private int idPaciente;
    private String rutPaciente;
    private String NombrePaciente;
    private int telefonoPaciente;
    private String Direccion;

    public Paciente(int idPaciente, String rutPaciente, String nombrePaciente, int telefonoPaciente, String direccion) {
        this.idPaciente = idPaciente;
        this.rutPaciente = rutPaciente;
        NombrePaciente = nombrePaciente;
        this.telefonoPaciente = telefonoPaciente;
        Direccion = direccion;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getRutPaciente() {
        return rutPaciente;
    }

    public void setRutPaciente(String rutPaciente) {
        this.rutPaciente = rutPaciente;
    }

    public String getNombrePaciente() {
        return NombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        NombrePaciente = nombrePaciente;
    }

    public int getTelefonoPaciente() {
        return telefonoPaciente;
    }

    public void setTelefonoPaciente(int telefonoPaciente) {
        this.telefonoPaciente = telefonoPaciente;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

}