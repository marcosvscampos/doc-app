package org.roguesoft.docapp.service;

import com.roguesoft.apiexception.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.roguesoft.docapp.application.dto.ClinicaDTO;
import org.roguesoft.docapp.application.dto.ResponseDTO;
import org.roguesoft.docapp.domain.mapper.DomainMapper;
import org.roguesoft.docapp.domain.mapper.FilterMapper;
import org.roguesoft.docapp.domain.service.DomainService;
import org.roguesoft.docapp.domain.service.impl.ClinicaService;
import org.roguesoft.docapp.infrastructure.model.Clinica;
import org.roguesoft.docapp.infrastructure.model.Convenio;
import org.roguesoft.docapp.infrastructure.repository.ClinicaRepository;
import org.roguesoft.docapp.infrastructure.repository.ConvenioRepository;
import org.roguesoft.docapp.utils.EntityBuilder;
import org.roguesoft.docapp.utils.RequestBuilder;
import org.roguesoft.docapp.utils.entity.ClinicaEntityBuilder;
import org.roguesoft.docapp.utils.entity.ConvenioEntityBuilder;
import org.roguesoft.docapp.utils.request.ClinicaRequestBuilder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.roguesoft.docapp.utils.entity.ClinicaEntityBuilder.CLINICA_ID;
import static org.wildfly.common.Assert.assertTrue;

@ExtendWith(MockitoExtension.class)
class ClinicaServiceTest {

    private DomainService<ClinicaDTO> clinicaDomainService;

    private RequestBuilder<ClinicaDTO> requestBuilder;

    private EntityBuilder<Clinica> clinicaEntityBuilder;

    private EntityBuilder<Convenio> convenioEntityBuilder;

    @Mock
    private ClinicaRepository clinicaRepository;

    @Mock
    private ConvenioRepository convenioRepository;

    @Mock
    private DomainMapper<ClinicaDTO, Clinica> domainMapper;

    @Mock
    private FilterMapper<Clinica> filterMapper;

    @BeforeEach
    public void setUp(){
        this.requestBuilder = new ClinicaRequestBuilder();
        this.clinicaEntityBuilder = new ClinicaEntityBuilder();
        this.convenioEntityBuilder = new ConvenioEntityBuilder();
        this.clinicaDomainService = new ClinicaService(
                this.clinicaRepository,
                this.convenioRepository,
                this.domainMapper,
                this.filterMapper
        );
    }

    @Test
    void testCreateClinicaSuccessful(){
        String nome = "Clinica Teste Junit";
        String email = "clinica.junit@gmail.com";
        String telefone = "193456271";
        ClinicaDTO request = requestBuilder.buildValid(nome, email, telefone);

        Clinica sampleClinica = clinicaEntityBuilder.build(nome, email, telefone);
        doReturn(sampleClinica).when(domainMapper).toModel(request);

        String cnpj = "22808339000151";
        Convenio sampleConvenio = convenioEntityBuilder.build(cnpj);
        doReturn(Optional.of(sampleConvenio)).when(convenioRepository).findById(request.getConvenioId());
        doReturn(sampleClinica).when(clinicaRepository).save(sampleClinica);

        ResponseDTO response = this.clinicaDomainService.create(request);

        verify(domainMapper, times(1)).toModel(any(ClinicaDTO.class));
        verify(convenioRepository, times(1)).findById(anyString());
        verify(clinicaRepository, times(1)).save(any(Clinica.class));

        assertTrue(response.getResource().contains( "/clinicas/" + CLINICA_ID));
    }

    @Test
    void testCreateClinicaConvenioInexistenteMustFail(){
        String nome = "Clinica Teste Junit";
        String email = "clinica.junit@gmail.com";
        String telefone = "193456271";
        ClinicaDTO request = requestBuilder.buildValid(nome, email, telefone);

        Clinica sampleClinica = clinicaEntityBuilder.build(nome, email, telefone);
        doReturn(sampleClinica).when(domainMapper).toModel(request);

        doReturn(Optional.empty()).when(convenioRepository).findById(request.getConvenioId());

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> this.clinicaDomainService.create(request));
        String expectedExceptionMessage = "Não foram encontrados convenios com ID: " + request.getConvenioId();
        String actualExceptionMessage = exception.getErrorMessage().getMessage();
        assertEquals(expectedExceptionMessage, actualExceptionMessage);
    }

    @Test
    void testBuscarClinicaPorIdSuccessful(){
        String nome = "Clinica Teste Junit";
        String email = "clinica.junit@gmail.com";
        String telefone = "193456271";
        Clinica sampleClinica = clinicaEntityBuilder.build(nome, email, telefone);

        doReturn(Optional.of(sampleClinica)).when(clinicaRepository).findById(CLINICA_ID);

        clinicaDomainService.findById(CLINICA_ID);

        verify(clinicaRepository, times(1)).findById(anyString());
    }

    @Test
    void testBuscarClinicaPorIdInvalidoMustFail(){
        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> this.clinicaDomainService.findById(CLINICA_ID));
        String expectedExceptionMessage = "Não foram encontrados clinicas com ID: " + CLINICA_ID;
        String actualExceptionMessage = exception.getErrorMessage().getMessage();
        assertEquals(expectedExceptionMessage, actualExceptionMessage);
    }

}
