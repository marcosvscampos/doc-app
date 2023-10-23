package org.roguesoft.docapp.domain.validation.pedidoagendamento.impl;

import com.roguesoft.apiexception.exception.PreconditionFailedException;
import lombok.RequiredArgsConstructor;
import org.roguesoft.docapp.application.dto.agendamento.PedidoAgendamentoDTO;
import org.roguesoft.docapp.domain.validation.pedidoagendamento.PedidoAgendamentoValidation;
import org.roguesoft.docapp.infrastructure.model.ConsultaMedica;
import org.roguesoft.docapp.infrastructure.repository.ConsultaMedicaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ConflitoHorarioExistenteValidation implements PedidoAgendamentoValidation {

    private final ConsultaMedicaRepository consultaMedicaRepository;

    @Override
    public void validate(PedidoAgendamentoDTO request) {
        List<ConsultaMedica> consultasMarcadas = consultaMedicaRepository.findByData(request.parseData());

        consultasMarcadas.forEach(ct -> {
            if(ct.getData().equals(request.parseData()) && ct.getHorario().equals(request.parseHorario())){
                throw new PreconditionFailedException("O horario " + request.getHorario() + " no dia " + request.getData() + " já está agendado para outro paciente");
            }
        });
    }
}
