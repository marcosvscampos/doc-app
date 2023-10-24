package org.roguesoft.docapp.domain.validation.pedidoagendamento.impl;

import com.roguesoft.apiexception.exception.PreconditionFailedException;
import org.roguesoft.docapp.application.dto.agendamento.PedidoAgendamentoDTO;
import org.roguesoft.docapp.domain.validation.pedidoagendamento.PedidoAgendamentoValidation;
import org.roguesoft.docapp.infrastructure.utils.DateParser;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class HorarioAgendamentoLimiteValidation implements PedidoAgendamentoValidation {
    @Override
    public void validate(final PedidoAgendamentoDTO request) {
        LocalTime horarioAgenda = DateParser.parseLocalTime(request.getHorario(), "HH:mm");
        LocalTime limiteInicio = LocalTime.of(9, 0);
        LocalTime limiteFim = LocalTime.of(17,0);

        if(horarioAgenda.isBefore(limiteInicio) || horarioAgenda.isAfter(limiteFim)){
            throw new PreconditionFailedException("O horário de agendamento deve estar entre 9h e 17h");
        }
    }
}
