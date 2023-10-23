package org.roguesoft.docapp.domain.mapper.impl.entity;

import org.roguesoft.docapp.application.dto.agendamento.AgendamentoDTO;
import org.roguesoft.docapp.domain.mapper.AgendaConsultaMapper;
import org.roguesoft.docapp.infrastructure.model.projection.AgendaConsulta;
import org.roguesoft.docapp.infrastructure.utils.DateParser;
import org.springframework.stereotype.Component;

@Component
public class AgendaConsultaDomainMapper implements AgendaConsultaMapper {
    @Override
    public AgendamentoDTO toAgendamentoDTO(final AgendaConsulta projection) {
        AgendamentoDTO agendamento = new AgendamentoDTO();
        agendamento.setConsultaId(projection.getConsultaId());
        agendamento.setMedicoId(projection.getMedicoId());
        agendamento.setNomeMedico(projection.getNomeMedico());
        agendamento.setEspecialidade(projection.getEspecialidade());
        agendamento.setPacienteId(projection.getPacienteId());
        agendamento.setNomePaciente(projection.getNomePaciente());
        agendamento.setStatus(projection.getStatus());
        agendamento.setData(DateParser.formatDate(projection.getData(), "dd/MM/yyyy"));
        agendamento.setHorario(DateParser.formatTime(projection.getHorario(), "HH:mm"));
        return agendamento;
    }
}
