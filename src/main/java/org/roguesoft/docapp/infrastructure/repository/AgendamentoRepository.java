package org.roguesoft.docapp.infrastructure.repository;

import org.roguesoft.docapp.infrastructure.model.projection.AgendaConsulta;
import org.roguesoft.docapp.infrastructure.model.queries.FindAgendaMedicoQuery;
import org.roguesoft.docapp.infrastructure.model.queries.FindAgendaPacienteQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AgendamentoRepository extends CrudRepository<AgendaConsulta, String> {

    @Query(value = FindAgendaMedicoQuery.QUERY_AGENDA_MEDICO, nativeQuery = true)
    List<AgendaConsulta> findMedicoAgendaByIdAndData(final String medicoId, final Date data);

    @Query(value = FindAgendaPacienteQuery.QUERY_AGENDA_PACIENTE, nativeQuery = true)
    List<AgendaConsulta> findPacienteAgendaByIdAndData(final String pacienteId, final Date data);
}
