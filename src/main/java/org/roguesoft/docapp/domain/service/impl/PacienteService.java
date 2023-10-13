package org.roguesoft.docapp.domain.service.impl;

import org.roguesoft.docapp.application.dto.PacienteDTO;
import org.roguesoft.docapp.application.dto.ResponseDTO;
import org.roguesoft.docapp.domain.mapper.DomainMapper;
import org.roguesoft.docapp.domain.service.DomainService;
import org.roguesoft.docapp.infrastructure.model.Paciente;
import org.roguesoft.docapp.infrastructure.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PacienteService implements DomainService<PacienteDTO> {

    private final PacienteRepository repository;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    private final static String PATH_NAME = "pacientes";

    private final DomainMapper<PacienteDTO, Paciente> domainMapper;

    public PacienteService(final PacienteRepository pacienteRepository,
                           DomainMapper<PacienteDTO, Paciente> domainMapper){
        this.repository = pacienteRepository;
        this.domainMapper = domainMapper;
    }

    @Override
    @Transactional
    public ResponseDTO create(PacienteDTO request) {
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
    public List<PacienteDTO> findAll() {
        return null;
    }
}
