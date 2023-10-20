package org.roguesoft.docapp.infrastructure.repository;

import org.roguesoft.docapp.infrastructure.model.ConsultaMedica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaMedicaRepository extends JpaRepository<ConsultaMedica, String> {

    
}
