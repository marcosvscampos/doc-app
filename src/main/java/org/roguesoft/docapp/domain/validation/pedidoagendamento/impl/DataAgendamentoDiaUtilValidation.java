package org.roguesoft.docapp.domain.validation.pedidoagendamento.impl;

import com.roguesoft.apiexception.exception.PreconditionFailedException;
import org.roguesoft.docapp.application.dto.agendamento.PedidoAgendamentoDTO;
import org.roguesoft.docapp.domain.validation.pedidoagendamento.PedidoAgendamentoValidation;
import org.roguesoft.docapp.infrastructure.utils.DateParser;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Component
public class DataAgendamentoDiaUtilValidation implements PedidoAgendamentoValidation {

    @Override
    public void validate(final PedidoAgendamentoDTO request) {
        LocalDate agendaDate = DateParser.parseLocalDate(request.getData(), "dd/MM/yyyy");
        DayOfWeek weekDay = agendaDate.getDayOfWeek();
        if(weekDay.equals(DayOfWeek.SATURDAY) || weekDay.equals(DayOfWeek.SUNDAY)){
            throw new PreconditionFailedException("Agendamento não pode ser realizado em dia de fim de semana (Sábado ou Domingo)");
        }
    }
}
