package org.roguesoft.docapp.domain.service.agendamento.retriever;

import org.roguesoft.docapp.infrastructure.model.projection.AgendaConsulta;
import org.roguesoft.docapp.infrastructure.repository.AgendamentoRepository;

import java.util.Date;
import java.util.List;

public class PacienteAgendaRetriever extends AgendaRetriever {
    public PacienteAgendaRetriever(AgendamentoRepository agendamentoRepository) {
        super(agendamentoRepository);
    }

    @Override
    public boolean checkId(String id) {
        return id.startsWith("IND");
    }

    @Override
    public List<AgendaConsulta> retrieveAgenda(String id, Date data) {
        return super.getRepository().findPacienteAgendaByIdAndData(id, data);
    }
}
