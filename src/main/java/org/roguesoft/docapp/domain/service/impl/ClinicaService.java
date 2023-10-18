package org.roguesoft.docapp.domain.service.impl;

import org.roguesoft.docapp.application.dto.ClinicaDTO;
import org.roguesoft.docapp.application.dto.ResponseDTO;
import org.roguesoft.docapp.application.dto.filter.Filter;
import org.roguesoft.docapp.domain.mapper.DomainMapper;
import org.roguesoft.docapp.domain.service.DomainService;
import org.roguesoft.docapp.infrastructure.model.Clinica;
import org.roguesoft.docapp.infrastructure.model.Convenio;
import org.roguesoft.docapp.infrastructure.repository.ClinicaRepository;
import org.roguesoft.docapp.infrastructure.repository.ConvenioRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClinicaService implements DomainService<ClinicaDTO> {

    private final ClinicaRepository repository;

    private final ConvenioRepository convenioRepository;

    private final DomainMapper<ClinicaDTO, Clinica> domainMapper;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    private static final String PATH_NAME = "clinicas";

    public ClinicaService(final ClinicaRepository clinicaRepository,
                          final ConvenioRepository convenioRepository,
                          final DomainMapper<ClinicaDTO, Clinica> clinicaDomainMapper){
        this.repository = clinicaRepository;
        this.convenioRepository = convenioRepository;
        this.domainMapper = clinicaDomainMapper;
    }

    @Override
    @Transactional
    public ResponseDTO create(final ClinicaDTO request) {
        Clinica clinica = domainMapper.toModel(request);

        Convenio convenio = convenioRepository.findById(request.getConvenioId())
                .orElseThrow(() -> new RuntimeException("Não foram encontrados convenios com ID: " + request.getConvenioId()));
        clinica.setConvenio(convenio);

        Clinica savedClinica = repository.save(clinica);

        return new ResponseDTO(contextPath, PATH_NAME, savedClinica.getId());
    }

    @Override
    public ClinicaDTO findById(String id) {
        Clinica clinica = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não foram encontrados clinicas com ID: " + id));
        return domainMapper.toDto(clinica);
    }

    @Override
    public Page<ClinicaDTO> findAll(final Filter filter) {
        return null;
    }
}
