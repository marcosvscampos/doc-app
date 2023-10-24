package org.roguesoft.docapp.infrastructure.repository;

import org.roguesoft.docapp.infrastructure.model.Feriado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FeriadoRepository extends JpaRepository<Feriado, Integer> {

    Optional<Feriado> findByDiaAndMes(final Integer dia, final Integer mes);

}
