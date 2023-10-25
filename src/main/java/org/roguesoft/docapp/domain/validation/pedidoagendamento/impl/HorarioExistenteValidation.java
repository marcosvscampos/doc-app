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
public class HorarioExistenteValidation implements PedidoAgendamentoValidation {

    private final ConsultaMedicaRepository consultaMedicaRepository;

    @Override
    public void validate(PedidoAgendamentoDTO request) {
        List<ConsultaMedica> consultasMarcadas = consultaMedicaRepository.findByDataAndMedicoId(request.parseData(), request.getMedicoId());

        consultasMarcadas.forEach(ct -> {
            if(ct.getData().equals(request.parseData()) && ct.getHorario().equals(request.parseHorario())){
                throw new PreconditionFailedException("Já existe um paciente com a agenda reservada no horario de "
                        + request.getHorario() + " no dia "
                        + request.getData() + " para o médico de código "
                        + request.getMedicoId());
            }
        });
    }
}
