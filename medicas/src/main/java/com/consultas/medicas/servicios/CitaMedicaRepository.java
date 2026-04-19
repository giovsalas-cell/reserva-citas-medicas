package com.consultas.medicas.servicios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.consultas.medicas.modelos.CitaMedica;
import com.consultas.medicas.modelos.Paciente;

@Repository
public interface CitaMedicaRepository extends JpaRepository<CitaMedica, Integer> {

    List<CitaMedica> findByDisponibilidad(int disponibilidad);

    List<CitaMedica> findAll();

}