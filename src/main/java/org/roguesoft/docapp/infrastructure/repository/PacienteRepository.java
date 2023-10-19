package org.roguesoft.docapp.infrastructure.repository;

import org.roguesoft.docapp.infrastructure.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, String>,
        PagingAndSortingRepository<Paciente, String>,
        JpaSpecificationExecutor<Paciente> {
}
