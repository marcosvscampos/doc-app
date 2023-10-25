package org.roguesoft.docapp.validation.pedidoagendamento;

import com.roguesoft.apiexception.exception.PreconditionFailedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.roguesoft.docapp.application.dto.agendamento.PedidoAgendamentoDTO;
import org.roguesoft.docapp.domain.validation.pedidoagendamento.PedidoAgendamentoValidation;
import org.roguesoft.docapp.domain.validation.pedidoagendamento.impl.DataAgendamentoDiasAntecedenciaValidation;
import org.roguesoft.docapp.utils.RequestBuilder;
import org.roguesoft.docapp.utils.request.PedidoAgendamentoRequestBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class DataAgendamentoDiasAntecedenciaValidationTest {

    private PedidoAgendamentoValidation validation;

    private RequestBuilder<PedidoAgendamentoDTO> requestBuilder;

    @BeforeEach
    public void setUp(){
        this.validation = new DataAgendamentoDiasAntecedenciaValidation();
        this.requestBuilder = new PedidoAgendamentoRequestBuilder();
    }

    @Test
    void testDataAgendamentoComMaisTresDiasAntecedenciaSuccessful(){
        PedidoAgendamentoDTO request = this.prepareRequest("31/10/2024");
        this.validation.validate(request);
    }

    @Test
    void testDataAgendamentoMenosTresDiasAntecedenciaMustFail(){
        PedidoAgendamentoDTO request = this.prepareRequest("25/10/2023");
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
        String expectedExceptionMessage = "Data de agendamento deve ser a partir de 3 dias da data atual";
        String actualExceptionMessage = exception.getErrorMessage().getMessage();
        assertEquals(expectedExceptionMessage, actualExceptionMessage);
    }

}
