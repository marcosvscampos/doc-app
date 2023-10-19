package org.roguesoft.docapp.infrastructure.repository;

import org.roguesoft.docapp.infrastructure.model.Clinica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicaRepository extends JpaRepository<Clinica, String>, PagingAndSortingRepository<Clinica, String> {
}
