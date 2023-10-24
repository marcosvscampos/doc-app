package org.roguesoft.docapp.domain.validation.pedidoagendamento.impl;

import com.roguesoft.apiexception.exception.PreconditionFailedException;
import lombok.RequiredArgsConstructor;
import org.roguesoft.docapp.application.dto.agendamento.PedidoAgendamentoDTO;
import org.roguesoft.docapp.domain.validation.pedidoagendamento.PedidoAgendamentoValidation;
import org.roguesoft.docapp.infrastructure.model.ConsultaMedica;
import org.roguesoft.docapp.infrastructure.model.Paciente;
import org.roguesoft.docapp.infrastructure.repository.ConsultaMedicaRepository;
import org.roguesoft.docapp.infrastructure.repository.PacienteRepository;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PacienteVariasConsultasPorDiaValidation implements PedidoAgendamentoValidation {

    private final PacienteRepository pacienteRepository;

    private final ConsultaMedicaRepository consultaMedicaRepository;

    @Override
    public void validate(PedidoAgendamentoDTO request) {
        String nome = request.getNome();
        Date dataNascimento = request.parseDataNascimento();
        Date dataConsulta = request.parseData();
        Optional<Paciente> pacienteOpt = pacienteRepository.findByNomeAndDataNascimento(nome, dataNascimento);

        if(pacienteOpt.isEmpty()){
            return;
        }

        Paciente paciente = pacienteOpt.get();

        List<ConsultaMedica> consultasMedicas = consultaMedicaRepository.findByDataByPacienteIdAndMedicoId(
                dataConsulta, paciente.getId(), request.getMedicoId());

        if(!consultasMedicas.isEmpty()) {
            throw new PreconditionFailedException(
                    "O paciente já possui uma consulta marcada no dia " + request.getData() + " para o médico de código " + request.getMedicoId());
        }
    }
}
