package org.roguesoft.docapp.helper;

import com.roguesoft.apiexception.exception.PreconditionFailedException;
import org.roguesoft.docapp.application.dto.agendamento.PedidoAgendamentoDTO;
import org.roguesoft.docapp.domain.validation.pedidoagendamento.impl.DataAgendamentoDiaUtilValidation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DataAgendamentoDiaUtilValidationTestHelper extends PedidoAgendamentoValidationTestHelper {

    public DataAgendamentoDiaUtilValidationTestHelper(){
        super(new DataAgendamentoDiaUtilValidation());
    }

    @Override
    public PedidoAgendamentoDTO prepareRequest(String data){
        String horario = "08:00";
        String medicoId = "MED-123456789-ABC";
        return super.getRequestBuilder().buildValid(data, horario, medicoId);
    }

    @Override
    public void verifyThrows(final PedidoAgendamentoDTO request){
        PreconditionFailedException exception = assertThrows(PreconditionFailedException.class,
                () -> super.getValidation().validate(request));
        String expectedExceptionMessage = "Agendamento não pode ser realizado em dia de fim de semana (Sábado ou Domingo)";
        String actualExceptionMessage = exception.getErrorMessage().getMessage();
        assertEquals(expectedExceptionMessage, actualExceptionMessage);
    }
}
