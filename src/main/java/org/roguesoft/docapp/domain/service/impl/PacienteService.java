package org.roguesoft.docapp.domain.service.impl;

import org.roguesoft.docapp.application.dto.PacienteDTO;
import org.roguesoft.docapp.application.dto.ResponseDTO;
import org.roguesoft.docapp.application.dto.filter.Filter;
import org.roguesoft.docapp.domain.mapper.DomainMapper;
import org.roguesoft.docapp.domain.mapper.FilterMapper;
import org.roguesoft.docapp.domain.service.DomainService;
import org.roguesoft.docapp.infrastructure.model.Paciente;
import org.roguesoft.docapp.infrastructure.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PacienteService implements DomainService<PacienteDTO> {

    private final PacienteRepository repository;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    private static final String PATH_NAME = "pacientes";

    private final DomainMapper<PacienteDTO, Paciente> domainMapper;

    private final FilterMapper<Paciente> filterMapper;

    public PacienteService(final PacienteRepository pacienteRepository,
                           final DomainMapper<PacienteDTO, Paciente> domainMapper,
                           final FilterMapper<Paciente> filterMapper){
        this.repository = pacienteRepository;
        this.domainMapper = domainMapper;
        this.filterMapper = filterMapper;
    }

    @Override
    @Transactional
    public ResponseDTO create(final PacienteDTO request) {
        Paciente paciente = repository.save(domainMapper.toModel(request));
        return new ResponseDTO(contextPath, PATH_NAME, paciente.getId());
    }

    @Override
    public PacienteDTO findById(String id) {
        Paciente paciente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não foram encontrados pacientes com ID: " + id));
        return domainMapper.toDto(paciente);
    }

    @Override
    public Page<PacienteDTO> findAll(final Filter filter) {
        Page<Paciente> result = repository.findAll(
                filterMapper.toSpecification(filter),
                filterMapper.toPageRequest(filter)
        );
        return result.map(domainMapper::toDto);
    }
}
