package org.roguesoft.docapp.domain.service.agendamento.retriever;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.roguesoft.docapp.infrastructure.model.projection.AgendaConsulta;
import org.roguesoft.docapp.infrastructure.repository.AgendamentoRepository;

import java.util.Date;
import java.util.List;

@Getter
@RequiredArgsConstructor
public abstract class AgendaRetriever {

    private final AgendamentoRepository repository;

    private AgendaRetriever next;

    public static AgendaRetriever chain(AgendaRetriever first, AgendaRetriever... chain){
        AgendaRetriever head = first;
        for (AgendaRetriever nextInChain: chain){
            head.next = nextInChain;
            head = nextInChain;
        }
        return first;
    }

    public List<AgendaConsulta> getAgenda(final String id, final Date data) {
        if(this.checkId(id)) {
            return this.retrieveAgenda(id, data);
        }
        return next.getAgenda(id, data);
    }

    public abstract boolean checkId(final String id);

    public abstract List<AgendaConsulta> retrieveAgenda(final String id, final Date data);
}
