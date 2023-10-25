package org.roguesoft.docapp.validation.pedidoagendamento;

import com.roguesoft.apiexception.exception.PreconditionFailedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.roguesoft.docapp.application.dto.agendamento.PedidoAgendamentoDTO;
import org.roguesoft.docapp.domain.validation.pedidoagendamento.PedidoAgendamentoValidation;
import org.roguesoft.docapp.domain.validation.pedidoagendamento.impl.HorarioExistenteValidation;
import org.roguesoft.docapp.infrastructure.model.ConsultaMedica;
import org.roguesoft.docapp.infrastructure.repository.ConsultaMedicaRepository;
import org.roguesoft.docapp.utils.EntityBuilder;
import org.roguesoft.docapp.utils.RequestBuilder;
import org.roguesoft.docapp.utils.entity.ConsultaMedicaEntityBuilder;
import org.roguesoft.docapp.utils.request.PedidoAgendamentoRequestBuilder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class HorarioExistenteValidationTest {

    private PedidoAgendamentoValidation validation;

    private RequestBuilder<PedidoAgendamentoDTO> requestBuilder;

    private EntityBuilder<List<ConsultaMedica>> entityBuilder;

    @Mock
    private ConsultaMedicaRepository repository;

    @BeforeEach
    public void setUp(){
        validation = new HorarioExistenteValidation(this.repository);
        requestBuilder = new PedidoAgendamentoRequestBuilder();
        entityBuilder = new ConsultaMedicaEntityBuilder();
    }

    @Test
    void testPedidoAgendamentoConsultaComOutroMedicoOutroHorarioSuccessful(){
        String dataAgendado = "12/12/2023";
        String horarioAgendado = "10:00";

        String dataConsulta = "12/12/2023";
        String horarioConsulta = "15:00";
        String medicoId = "MED-172817221-BNH";
        PedidoAgendamentoDTO request = requestBuilder.buildValid(dataConsulta, horarioConsulta, medicoId);

        List<ConsultaMedica> sampleConsultasMedicas = entityBuilder.build(dataAgendado, horarioAgendado);
        doReturn(sampleConsultasMedicas).when(repository).findByDataAndMedicoId(request.parseData(), request.getMedicoId());

        validation.validate(request);

        verify(repository, times(1)).findByDataAndMedicoId(any(), eq(medicoId));

    }

    @Test
    void testPedidoAgendamentoConsultaComMesmoHorarioMustFail(){
        String data = "12/12/2023";
        String horario = "10:00";
        String medicoId = "MED-12345678-ABC";
        PedidoAgendamentoDTO request = requestBuilder.buildValid(data, horario, medicoId);

        List<ConsultaMedica> sampleConsultasMedicas = entityBuilder.build(data, horario);
        doReturn(sampleConsultasMedicas).when(repository).findByDataAndMedicoId(request.parseData(), request.getMedicoId());

        PreconditionFailedException exception = assertThrows(PreconditionFailedException.class, () -> validation.validate(request));
        String actualExceptionMessage = "Já existe um paciente com a agenda reservada no horario de " + request.getHorario()
                + " no dia " + request.getData()
                + " para o médico de código " + request.getMedicoId();
        String exceptionErrorMessage = exception.getErrorMessage().getMessage();
        assertEquals(actualExceptionMessage, exceptionErrorMessage);
    }

}
