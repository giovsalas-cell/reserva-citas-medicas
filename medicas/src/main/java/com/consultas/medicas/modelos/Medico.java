package com.consultas.medicas.modelos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Size;

@Entity
@Table(name = "MEDICO")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MEDICO")
    private int idMedico;
    @NotBlank(message = "rut de medico obligatorio")
    @Size(min = 1, max = 10, message = "rut incorrecto")
    @Column(name = "rut_medico")
    private String rutMedico;
    @NotBlank(message = "nombre de medico obligatorio")
    @Size(min = 1, max = 50, message = "nombre almenos debe tener mas de 1 caracter")
    @Column(name = "nombre_medico")
    private String nombreMedico;
    @NotBlank(message = "especialidad de medico obligatorio")
    @Size(min = 1, max = 50, message = "especialidad almenos debe tener mas de 1 caracter")
    @Column(name = "especialidad")
    private String especialidad;
    @OneToMany(mappedBy = "medico")
    @JsonIgnore
    private List<CitaMedica> citas;

    public Medico() {
    }

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
