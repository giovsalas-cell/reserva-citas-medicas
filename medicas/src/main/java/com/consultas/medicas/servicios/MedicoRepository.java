package com.consultas.medicas.servicios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.consultas.medicas.modelos.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Integer> {

}
