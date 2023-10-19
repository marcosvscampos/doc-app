package org.roguesoft.docapp.domain.mapper.impl.entity;

import org.roguesoft.docapp.application.dto.ClinicaDTO;
import org.roguesoft.docapp.application.dto.MedicoDTO;
import org.roguesoft.docapp.domain.mapper.DomainMapper;
import org.roguesoft.docapp.infrastructure.model.Clinica;
import org.roguesoft.docapp.infrastructure.model.Medico;
import org.springframework.stereotype.Component;

@Component
public class MedicoDomainMapper implements DomainMapper<MedicoDTO, Medico>  {

    private DomainMapper<ClinicaDTO, Clinica> clinicaDomainMapper;

    public MedicoDomainMapper(final DomainMapper<ClinicaDTO, Clinica> clinicaDomainMapper){
        this.clinicaDomainMapper = clinicaDomainMapper;
    }

    @Override
    public Medico toModel(MedicoDTO request) {
        Medico medico = new Medico();
        medico.setNome(request.getNome());
        medico.setEspecialidade(request.getEspecialidade());

        return medico;
    }

    @Override
    public MedicoDTO toDto(Medico model) {
        ClinicaDTO clinica = clinicaDomainMapper.toDto(model.getClinica());
        return MedicoDTO.medicoBuilder()
                .id(model.getId())
                .nome(model.getNome())
                .especialidade(model.getEspecialidade())
                .clinica(clinica)
                .build();
    }
}
