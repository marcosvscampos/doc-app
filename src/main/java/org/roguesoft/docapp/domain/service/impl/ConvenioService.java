package org.roguesoft.docapp.domain.service.impl;

import com.roguesoft.apiexception.exception.NotFoundException;
import org.roguesoft.docapp.application.dto.ConvenioDTO;
import org.roguesoft.docapp.application.dto.ResponseDTO;
import org.roguesoft.docapp.application.dto.filter.Filter;
import org.roguesoft.docapp.domain.mapper.DomainMapper;
import org.roguesoft.docapp.domain.mapper.FilterMapper;
import org.roguesoft.docapp.domain.service.DomainService;
import org.roguesoft.docapp.infrastructure.model.Convenio;
import org.roguesoft.docapp.infrastructure.model.Paciente;
import org.roguesoft.docapp.infrastructure.repository.ConvenioRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConvenioService implements DomainService<ConvenioDTO> {

    private final ConvenioRepository repository;

    private final DomainMapper<ConvenioDTO, Convenio> domainMapper;

    private final FilterMapper<Convenio> filterMapper;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    private static final String PATH_NAME = "convenios";

    public ConvenioService(final ConvenioRepository convenioRepository,
                           final DomainMapper<ConvenioDTO, Convenio> convenioDomainMapper,
                           final FilterMapper<Convenio> convenioFilterMapper) {
        this.repository = convenioRepository;
        this.domainMapper = convenioDomainMapper;
        this.filterMapper = convenioFilterMapper;
    }

    @Override
    @Transactional
    public ResponseDTO create(final ConvenioDTO request) {
        Convenio convenio = repository.save(domainMapper.toModel(request));
        return new ResponseDTO(contextPath, PATH_NAME, convenio.getId());
    }

    @Override
    public ConvenioDTO findById(String id) {
        Convenio convenio = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não foram encontrados convenios com ID: " + id));
        return domainMapper.toDto(convenio);
    }

    @Override
    public Page<ConvenioDTO> findAll(final Filter filter) {
        Page<Convenio> result = repository.findAll(
                filterMapper.toSpecification(filter),
                filterMapper.toPageRequest(filter)
        );
        return result.map(domainMapper::toDto);
    }
}
