package org.roguesoft.docapp.domain.service.agendamento.retriever;

import org.roguesoft.docapp.infrastructure.model.projection.AgendaConsulta;
import org.roguesoft.docapp.infrastructure.repository.AgendamentoRepository;

import java.util.Date;
import java.util.List;

public class MedicoAgendaRetriever extends AgendaRetriever {

    public MedicoAgendaRetriever(final AgendamentoRepository agendamentoRepository) {
        super(agendamentoRepository);
    }

    @Override
    public boolean checkId(String id) {
        return id.startsWith("MED");
    }

    @Override
    public List<AgendaConsulta> retrieveAgenda(String id, Date data) {
        return super.getRepository().findMedicoAgendaByIdAndData(id, data);
    }
}
