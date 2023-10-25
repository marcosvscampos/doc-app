package org.roguesoft.docapp.validation.pedidoagendamento;

import com.roguesoft.apiexception.exception.PreconditionFailedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.roguesoft.docapp.application.dto.agendamento.PedidoAgendamentoDTO;
import org.roguesoft.docapp.domain.validation.pedidoagendamento.PedidoAgendamentoValidation;
import org.roguesoft.docapp.domain.validation.pedidoagendamento.impl.HorarioAgendamentoLimiteValidation;
import org.roguesoft.docapp.utils.RequestBuilder;
import org.roguesoft.docapp.utils.request.PedidoAgendamentoRequestBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class HorarioAgendamentoLimiteValidationTest {

    private PedidoAgendamentoValidation validation;

    private RequestBuilder<PedidoAgendamentoDTO> requestBuilder;

    @BeforeEach
    public void setUp(){
        this.validation = new HorarioAgendamentoLimiteValidation();
        this.requestBuilder = new PedidoAgendamentoRequestBuilder();
    }

    @Test
    void testPedidoAgendamentoDentroHorarioComercialSuccessful(){
        PedidoAgendamentoDTO request = this.prepareRequest("09:00");
        validation.validate(request);
    }

    @Test
    void testPedidoAgendamentoAntesHorarioPermitidoMustFail(){
        PedidoAgendamentoDTO request = this.prepareRequest("06:00");
        this.verifyThrows(request);
    }

    @Test
    void testPedidoAgendamentoDepoisHorarioPermitidoMustFail(){
        PedidoAgendamentoDTO request = this.prepareRequest("19:00");
        this.verifyThrows(request);
    }

    private PedidoAgendamentoDTO prepareRequest(String horario){
        String data = "10/10/2024";
        String medicoId = "MED-123456789-ABC";
        return this.requestBuilder.buildValid(data, horario, medicoId);
    }

    private void verifyThrows(final PedidoAgendamentoDTO request){
        PreconditionFailedException exception = assertThrows(PreconditionFailedException.class,
                () -> validation.validate(request));
        String expectedExceptionMessage = "O horário de agendamento deve estar entre 9h e 17h";
        String actualExceptionMessage = exception.getErrorMessage().getMessage();
        assertEquals(expectedExceptionMessage, actualExceptionMessage);
    }

}
