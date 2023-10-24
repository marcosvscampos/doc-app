package org.roguesoft.docapp.domain.validation.pedidoagendamento;

import org.roguesoft.docapp.application.dto.agendamento.PedidoAgendamentoDTO;

public interface PedidoAgendamentoValidation {

    void validate(final PedidoAgendamentoDTO request);

}
