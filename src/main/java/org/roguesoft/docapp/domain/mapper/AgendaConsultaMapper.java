package org.roguesoft.docapp.domain.mapper;

import org.roguesoft.docapp.application.dto.agendamento.AgendamentoDTO;
import org.roguesoft.docapp.infrastructure.model.projection.AgendaConsulta;

public interface AgendaConsultaMapper {

    AgendamentoDTO toAgendamentoDTO(final AgendaConsulta projection);

}
