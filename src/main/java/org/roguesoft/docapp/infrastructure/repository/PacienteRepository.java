package org.roguesoft.docapp.infrastructure.repository;

import org.roguesoft.docapp.infrastructure.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, String>,
        PagingAndSortingRepository<Paciente, String>,
        JpaSpecificationExecutor<Paciente> {

    @Query(value = "SELECT p FROM Paciente p" +
            " LEFT JOIN Individuo i on i.id = p.id" +
            " WHERE p.dataNascimento = :dataNascimento" +
            " AND i.nome = :nome")
    Optional<Paciente> findByNomeAndDataNascimento(final String nome, final Date dataNascimento);
}
