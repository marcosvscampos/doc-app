package org.roguesoft.docapp.domain.validation.pedidoagendamento.impl;

import com.roguesoft.apiexception.exception.PreconditionFailedException;
import org.roguesoft.docapp.application.dto.agendamento.PedidoAgendamentoDTO;
import org.roguesoft.docapp.domain.validation.pedidoagendamento.PedidoAgendamentoValidation;
import org.roguesoft.docapp.infrastructure.utils.DateParser;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataAgendamentoDiasAntecedenciaValidation implements PedidoAgendamentoValidation {

    @Override
    public void validate(PedidoAgendamentoDTO request) {
        LocalDate agendaDate = DateParser.parseLocalDate(request.getData(), "dd/MM/yyyy");
        LocalDate datePlusThreeDays = LocalDate.now().plusDays(3);
        if(agendaDate.isBefore(datePlusThreeDays)){
            throw new PreconditionFailedException("Data de agendamento deve ser a partir de 3 dias da data atual");
        }
    }
}
