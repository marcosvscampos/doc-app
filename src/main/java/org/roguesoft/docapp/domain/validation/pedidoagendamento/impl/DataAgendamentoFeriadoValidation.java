package org.roguesoft.docapp.domain.validation.pedidoagendamento.impl;

import com.roguesoft.apiexception.exception.PreconditionFailedException;
import lombok.RequiredArgsConstructor;
import org.roguesoft.docapp.application.dto.agendamento.PedidoAgendamentoDTO;
import org.roguesoft.docapp.domain.validation.pedidoagendamento.PedidoAgendamentoValidation;
import org.roguesoft.docapp.infrastructure.model.Feriado;
import org.roguesoft.docapp.infrastructure.repository.FeriadoRepository;
import org.roguesoft.docapp.infrastructure.utils.DateParser;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DataAgendamentoFeriadoValidation implements PedidoAgendamentoValidation {

    private final FeriadoRepository repository;

    @Override
    public void validate(final PedidoAgendamentoDTO request) {
        LocalDate agendaDate = DateParser.parseLocalDate(request.getData(), "dd/MM/yyyy");
        Integer dia = agendaDate.getDayOfMonth();
        Integer mes = agendaDate.getMonthValue();

        Optional<Feriado> feriado = repository.findByDiaAndMes(dia, mes);
        if(feriado.isPresent()) {
            throw new PreconditionFailedException("Agendamento não pode ser realizado em dias de feriado: "
                    + request.getData() + " - " + feriado.get().getDescription());
        }
    }
}
