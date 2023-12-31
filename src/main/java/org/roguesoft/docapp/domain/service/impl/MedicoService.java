package org.roguesoft.docapp.domain.service.impl;

import com.roguesoft.apiexception.exception.NotFoundException;
import org.roguesoft.docapp.application.dto.MedicoDTO;
import org.roguesoft.docapp.application.dto.ResponseDTO;
import org.roguesoft.docapp.application.dto.filter.Filter;
import org.roguesoft.docapp.domain.mapper.DomainMapper;
import org.roguesoft.docapp.domain.mapper.FilterMapper;
import org.roguesoft.docapp.domain.service.DomainService;
import org.roguesoft.docapp.infrastructure.model.Clinica;
import org.roguesoft.docapp.infrastructure.model.Medico;
import org.roguesoft.docapp.infrastructure.repository.ClinicaRepository;
import org.roguesoft.docapp.infrastructure.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class MedicoService implements DomainService<MedicoDTO> {

    private final MedicoRepository repository;

    private final ClinicaRepository clinicaRepository;

    private final DomainMapper<MedicoDTO, Medico> domainMapper;

    private final FilterMapper<Medico> filterMapper;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    private static final String PATH_NAME = "medicos";

    public MedicoService(final MedicoRepository medicoRepository,
                           final ClinicaRepository clinicaRepository,
                           final DomainMapper<MedicoDTO, Medico> medicoDomainMapper,
                           final FilterMapper<Medico> medicoFilterMapper) {
        this.repository = medicoRepository;
        this.clinicaRepository = clinicaRepository;
        this.domainMapper = medicoDomainMapper;
        this.filterMapper = medicoFilterMapper;
    }

    @Override
    public ResponseDTO create(final MedicoDTO request) {
         Medico medico = domainMapper.toModel(request);

         Clinica clinica = clinicaRepository.findById(request.getClinicaId())
                .orElseThrow(() -> new NotFoundException("Não foram encontrados clinicas com ID: " + request.getClinicaId()));

         medico.setClinica(clinica);

         Medico savedMedico = repository.save(medico);
         return new ResponseDTO(contextPath, PATH_NAME, savedMedico.getId());
    }

    @Override
    public MedicoDTO findById(String id) {
        Medico medico = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não foram encontrados medicos com ID: " + id));
        return domainMapper.toDto(medico);
    }

    @Override
    public Page<MedicoDTO> findAll(Filter filter) {
        Page<Medico> result = repository.findAll(
                filterMapper.toSpecification(filter),
                filterMapper.toPageRequest(filter)
        );
        return result.map(domainMapper::toDto);
    }
}
