package org.roguesoft.docapp.infrastructure.repository;

import org.roguesoft.docapp.infrastructure.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, String>, PagingAndSortingRepository<Medico, String> {
}
