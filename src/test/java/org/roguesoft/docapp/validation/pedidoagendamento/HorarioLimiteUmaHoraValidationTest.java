package org.roguesoft.docapp.validation.pedidoagendamento;

import com.roguesoft.apiexception.exception.PreconditionFailedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.roguesoft.docapp.application.dto.agendamento.PedidoAgendamentoDTO;
import org.roguesoft.docapp.domain.validation.pedidoagendamento.PedidoAgendamentoValidation;
import org.roguesoft.docapp.domain.validation.pedidoagendamento.impl.HorarioLimiteUmaHoraValidation;
import org.roguesoft.docapp.utils.RequestBuilder;
import org.roguesoft.docapp.utils.request.PedidoAgendamentoRequestBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class HorarioLimiteUmaHoraValidationTest {

    private PedidoAgendamentoValidation validation;

    private RequestBuilder<PedidoAgendamentoDTO> requestBuilder;

    @BeforeEach
    public void setUp(){
        this.validation = new HorarioLimiteUmaHoraValidation();
        this.requestBuilder = new PedidoAgendamentoRequestBuilder();
    }

    @Test
    void testPedidoAgendamentoHorarioCorretoSuccessful(){
        PedidoAgendamentoDTO request = this.prepareRequest("10:00");
        this.validation.validate(request);
    }

    @Test
    void testPedidoAgendamentoHorarioIncorretoMustFail(){
        PedidoAgendamentoDTO request = this.prepareRequest("11:20");
        PreconditionFailedException exception = assertThrows(PreconditionFailedException.class,
                () -> validation.validate(request));
        String expectedExceptionMessage = "O horário do agendamento não está no limite de 1 hora completa: " + request.getHorario();
        String actualExceptionMessage = exception.getErrorMessage().getMessage();
        assertEquals(expectedExceptionMessage, actualExceptionMessage);
    }

    private PedidoAgendamentoDTO prepareRequest(String horario){
        String data = "10/10/2024";
        String medicoId = "MED-123456789-ABC";
        return this.requestBuilder.buildValid(data, horario, medicoId);
    }

}
