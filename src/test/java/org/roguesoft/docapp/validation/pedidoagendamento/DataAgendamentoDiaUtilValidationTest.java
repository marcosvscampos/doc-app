package org.roguesoft.docapp.validation.pedidoagendamento;

import com.roguesoft.apiexception.exception.PreconditionFailedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.roguesoft.docapp.application.dto.agendamento.PedidoAgendamentoDTO;
import org.roguesoft.docapp.domain.validation.pedidoagendamento.PedidoAgendamentoValidation;
import org.roguesoft.docapp.domain.validation.pedidoagendamento.impl.DataAgendamentoDiaUtilValidation;
import org.roguesoft.docapp.utils.RequestBuilder;
import org.roguesoft.docapp.utils.request.PedidoAgendamentoRequestBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class DataAgendamentoDiaUtilValidationTest {

    private PedidoAgendamentoValidation validation;

    private RequestBuilder<PedidoAgendamentoDTO> requestBuilder;

    @BeforeEach
    public void setUp(){
        this.validation = new DataAgendamentoDiaUtilValidation();
        this.requestBuilder = new PedidoAgendamentoRequestBuilder();
    }

    @Test
    void testDataAgendamentoDiaUtilSuccessful(){
        PedidoAgendamentoDTO request = this.prepareRequest("01/11/2023");
        this.validation.validate(request);
    }

    @Test
    void testDataAgendamentoSabadoMustFail(){
        PedidoAgendamentoDTO request = this.prepareRequest("28/10/2023");
        this.verifyThrows(request);
    }

    @Test
    void testDataAgendamentoDomingoMustFail(){
        PedidoAgendamentoDTO request = this.prepareRequest("29/10/2023");
        this.verifyThrows(request);
    }

    private PedidoAgendamentoDTO prepareRequest(String data){
        String horario = "08:00";
        String medicoId = "MED-123456789-ABC";
        return this.requestBuilder.buildValid(data, horario, medicoId);
    }

    private void verifyThrows(final PedidoAgendamentoDTO request){
        PreconditionFailedException exception = assertThrows(PreconditionFailedException.class,
                () -> validation.validate(request));
        String expectedExceptionMessage = "Agendamento não pode ser realizado em dia de fim de semana (Sábado ou Domingo)";
        String actualExceptionMessage = exception.getErrorMessage().getMessage();
        assertEquals(expectedExceptionMessage, actualExceptionMessage);
    }
}
