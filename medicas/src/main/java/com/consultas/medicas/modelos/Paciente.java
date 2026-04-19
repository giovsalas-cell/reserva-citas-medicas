package com.consultas.medicas.modelos;

import java.util.List;

import org.springframework.context.annotation.EnableLoadTimeWeaving;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "paciente")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPaciente;
    @NotBlank(message = "rut de paciente obligatorio")
    @Size(min = 1, max = 10, message = "rut incorrecto")
    @Column(name = "rut_paciente")
    private String rutPaciente;
    @NotBlank(message = "nombre de paciente obligatorio")
    @Size(min = 1, max = 50, message = "nombre almenos debe tener mas de 1 caracter")
    @Column(name = "nombre_paciente")
    private String nombrePaciente;

    @NotNull(message = "telefono de paciente obligatorio")
    @Column(name = "telefono_paciente")
    private Integer telefonoPaciente;

    @NotBlank(message = "direccion de paciente obligatorio")
    @Size(min = 1, max = 200, message = "direccion almenos debe tener mas de 1 caracter")
    @Column(name = "direccion")
    private String direccion;

    @OneToMany(mappedBy = "paciente")
    @JsonIgnore
    private List<CitaMedica> citas;

    public Paciente() {
    }

    public Paciente(int idPaciente, String rutPaciente, String nombrePaciente,
            Integer telefonoPaciente, String direccion, List<CitaMedica> citas) {
        this.idPaciente = idPaciente;
        this.rutPaciente = rutPaciente;
        this.nombrePaciente = nombrePaciente;
        this.telefonoPaciente = telefonoPaciente;
        this.direccion = direccion;
        this.citas = citas;
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
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public Integer getTelefonoPaciente() {
        return telefonoPaciente;
    }

    public void setTelefonoPaciente(Integer telefonoPaciente) {
        this.telefonoPaciente = telefonoPaciente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<CitaMedica> getCitas() {
        return citas;
    }

    public void setCitas(List<CitaMedica> citas) {
        this.citas = citas;
    }

}