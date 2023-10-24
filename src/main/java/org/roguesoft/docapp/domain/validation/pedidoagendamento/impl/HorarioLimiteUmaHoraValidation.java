package org.roguesoft.docapp.domain.validation.pedidoagendamento.impl;

import com.roguesoft.apiexception.exception.PreconditionFailedException;
import org.roguesoft.docapp.application.dto.agendamento.PedidoAgendamentoDTO;
import org.roguesoft.docapp.domain.validation.pedidoagendamento.PedidoAgendamentoValidation;
import org.springframework.stereotype.Component;

@Component
public class HorarioLimiteUmaHoraValidation implements PedidoAgendamentoValidation {
    @Override
    public void validate(PedidoAgendamentoDTO request) {
        String [] horarioParts = request.getHorario().split(":");
        String minutos = horarioParts[1];
        if(!minutos.equals("00")){
            throw new PreconditionFailedException("O horário do agendamento não está no limite de 1 hora completa: " + request.getHorario());
        }
    }
}
