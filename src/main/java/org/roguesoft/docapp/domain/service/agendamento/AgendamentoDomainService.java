package org.roguesoft.docapp.domain.service.agendamento;

import org.roguesoft.docapp.application.dto.agendamento.AgendamentoDTO;
import org.roguesoft.docapp.application.dto.filter.Filter;

import java.util.List;

public interface AgendamentoDomainService {

    List<AgendamentoDTO> buscarAgendamentosPorId(final Filter filter, final String id);

}
