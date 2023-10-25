package org.roguesoft.docapp.utils.request;

import org.roguesoft.docapp.application.dto.agendamento.PedidoAgendamentoDTO;
import org.roguesoft.docapp.utils.RequestBuilder;

public class PedidoAgendamentoRequestBuilder implements RequestBuilder<PedidoAgendamentoDTO> {

    @Override
    public PedidoAgendamentoDTO buildValid(String... params) {
        return PedidoAgendamentoDTO.builder()
                .nome("Josiane Santos")
                .dataNascimento("20/04/1990")
                .data(params[0])
                .horario(params[1])
                .medicoId(params[2])
                .build();
    }
}
