package org.roguesoft.docapp.validation.pedidoagendamento;

import com.roguesoft.apiexception.exception.PreconditionFailedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.roguesoft.docapp.application.dto.agendamento.PedidoAgendamentoDTO;
import org.roguesoft.docapp.domain.validation.pedidoagendamento.PedidoAgendamentoValidation;
import org.roguesoft.docapp.domain.validation.pedidoagendamento.impl.PacienteVariasConsultasPorDiaValidation;
import org.roguesoft.docapp.infrastructure.model.ConsultaMedica;
import org.roguesoft.docapp.infrastructure.model.Paciente;
import org.roguesoft.docapp.infrastructure.repository.ConsultaMedicaRepository;
import org.roguesoft.docapp.infrastructure.repository.PacienteRepository;
import org.roguesoft.docapp.utils.DateParser;
import org.roguesoft.docapp.utils.EntityBuilder;
import org.roguesoft.docapp.utils.RequestBuilder;
import org.roguesoft.docapp.utils.entity.ConsultaMedicaEntityBuilder;
import org.roguesoft.docapp.utils.entity.PacienteEntityBuilder;
import org.roguesoft.docapp.utils.request.PedidoAgendamentoRequestBuilder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PacienteVariasConsultasPorDiaValidationTest {

    private RequestBuilder<PedidoAgendamentoDTO> requestBuilder;

    private EntityBuilder<Optional<Paciente>> pacienteEntityBuilder;

    private EntityBuilder<List<ConsultaMedica>> consultaMedicaBuilder;

    private PedidoAgendamentoValidation validation;

    @Mock
    private PacienteRepository pacienteRepository;

    @Mock
    private ConsultaMedicaRepository consultaMedicaRepository;

    @BeforeEach
    public void setUp(){
        this.validation = new PacienteVariasConsultasPorDiaValidation(
                this.pacienteRepository, this.consultaMedicaRepository
        );
        this.requestBuilder = new PedidoAgendamentoRequestBuilder();
        this.pacienteEntityBuilder = new PacienteEntityBuilder();
        this.consultaMedicaBuilder = new ConsultaMedicaEntityBuilder();
    }

    @Test
    void testPedidoAgendamentoPacienteSemConsultasSuccessful(){
        String dataConsulta = "12/12/2023";
        String horarioConsulta = "15:00";
        String medicoId = "MED-172817221-BNH";
        PedidoAgendamentoDTO request = requestBuilder.buildValid(dataConsulta, horarioConsulta, medicoId);

        doReturn(Optional.empty()).when(pacienteRepository)
                .findByNomeAndDataNascimento(request.getNome(), request.parseDataNascimento());

        validation.validate(request);

        verify(pacienteRepository, times(1)).findByNomeAndDataNascimento(anyString(), any());
        verify(consultaMedicaRepository, never()).findByDataByPacienteIdAndMedicoId(any(), anyString(), anyString());
    }

    @Test
    void testPacienteComConsultaMedicosDiferentesMesmoDiaSuccessful(){
        String dataConsulta = "12/12/2023";
        String horarioConsulta = "15:00";
        String medicoId = "MED-172817221-BNH";
        PedidoAgendamentoDTO request = requestBuilder.buildValid(dataConsulta, horarioConsulta, medicoId);

        String dataNascimento = "20/04/1990";
        Optional<Paciente> pacienteOpt = pacienteEntityBuilder.build(dataNascimento);
        doReturn(pacienteOpt).when(pacienteRepository)
                .findByNomeAndDataNascimento(request.getNome(),
                        DateParser.parseData(dataNascimento));

        Paciente samplePaciente = pacienteOpt.get();
        doReturn(Collections.emptyList()).when(consultaMedicaRepository)
                .findByDataByPacienteIdAndMedicoId(
                        DateParser.parseData(dataConsulta),
                        samplePaciente.getId(),
                        request.getMedicoId());

        validation.validate(request);

        verify(pacienteRepository, times(1)).findByNomeAndDataNascimento(anyString(), any());
        verify(consultaMedicaRepository, times(1)).findByDataByPacienteIdAndMedicoId(any(), anyString(), anyString());
    }

    @Test
    void testPacienteComConsultaMesmoMedicoMesmoDiaMustFail(){
        String horarioConflito = "13:00";

        String dataConsulta = "12/12/2023";
        String horarioConsulta = "15:00";
        String medicoId = "MED-172817221-BNH";
        PedidoAgendamentoDTO request = requestBuilder.buildValid(dataConsulta, horarioConsulta, medicoId);

        String dataNascimento = "20/04/1990";
        Optional<Paciente> pacienteOpt = pacienteEntityBuilder.build(dataNascimento);
        doReturn(pacienteOpt).when(pacienteRepository)
                .findByNomeAndDataNascimento(request.getNome(),
                        DateParser.parseData(dataNascimento));

        Paciente samplePaciente = pacienteOpt.get();

        List<ConsultaMedica> sampleConsultasMedicas = consultaMedicaBuilder.build(dataConsulta, horarioConflito);
        doReturn(sampleConsultasMedicas).when(consultaMedicaRepository)
                .findByDataByPacienteIdAndMedicoId(
                        DateParser.parseData(dataConsulta),
                        samplePaciente.getId(),
                        request.getMedicoId());

        PreconditionFailedException exception = assertThrows(PreconditionFailedException.class, () -> validation.validate(request));
        String actualExceptionMessage = "O paciente já possui uma consulta marcada no dia " + request.getData() +
                " para o médico de código " + request.getMedicoId();
        String exceptionErrorMessage = exception.getErrorMessage().getMessage();
        assertEquals(actualExceptionMessage, exceptionErrorMessage);

    }
}
