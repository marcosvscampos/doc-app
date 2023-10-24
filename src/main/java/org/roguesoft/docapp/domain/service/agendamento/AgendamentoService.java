package org.roguesoft.docapp.domain.service.agendamento;

import org.roguesoft.docapp.application.dto.agendamento.AgendamentoDTO;
import org.roguesoft.docapp.application.dto.filter.AgendamentoFilter;
import org.roguesoft.docapp.application.dto.filter.Filter;
import org.roguesoft.docapp.domain.mapper.impl.entity.AgendaConsultaDomainMapper;
import org.roguesoft.docapp.domain.service.agendamento.retriever.AgendaRetriever;
import org.roguesoft.docapp.domain.service.agendamento.retriever.MedicoAgendaRetriever;
import org.roguesoft.docapp.domain.service.agendamento.retriever.PacienteAgendaRetriever;
import org.roguesoft.docapp.infrastructure.model.projection.AgendaConsulta;
import org.roguesoft.docapp.infrastructure.repository.AgendamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoService implements AgendamentoDomainService {

    private final AgendaRetriever agendaRetriever;

    private final AgendaConsultaDomainMapper domainMapper;

    public AgendamentoService(final AgendamentoRepository agendamentoRepository,
                              final AgendaConsultaDomainMapper agendaConsultaDomainMapper){
        this.agendaRetriever = AgendaRetriever.chain(
                new MedicoAgendaRetriever(agendamentoRepository),
                new PacienteAgendaRetriever(agendamentoRepository)
        );
        this.domainMapper = agendaConsultaDomainMapper;
    }

    @Override
    public List<AgendamentoDTO> buscarAgendamentosPorId(Filter filter, String id) {
        AgendamentoFilter agendamentoFilter = (AgendamentoFilter) filter;
        List<AgendaConsulta> agendaConsultas = agendaRetriever.getAgenda(id, agendamentoFilter.parseData());

        return agendaConsultas.stream().map(domainMapper::toAgendamentoDTO).toList();
    }
}
