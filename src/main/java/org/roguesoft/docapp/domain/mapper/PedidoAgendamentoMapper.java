package org.roguesoft.docapp.domain.mapper;

import org.roguesoft.docapp.application.dto.PedidoAgendamentoDTO;
import org.roguesoft.docapp.infrastructure.model.Paciente;

public interface PedidoAgendamentoMapper {

    Paciente toPacienteAgendamento(final PedidoAgendamentoDTO request);

}
