package org.roguesoft.docapp.helper;

import com.roguesoft.apiexception.exception.PreconditionFailedException;
import org.roguesoft.docapp.application.dto.agendamento.PedidoAgendamentoDTO;
import org.roguesoft.docapp.domain.validation.pedidoagendamento.impl.HorarioAgendamentoLimiteValidation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HorarioAgendamentoLimiteValidationTestHelper extends PedidoAgendamentoValidationTestHelper {

    public HorarioAgendamentoLimiteValidationTestHelper(){
        super(new HorarioAgendamentoLimiteValidation());
    }

    @Override
    public PedidoAgendamentoDTO prepareRequest(String horario){
        String data = "10/10/2024";
        String medicoId = "MED-123456789-ABC";
        return super.getRequestBuilder().buildValid(data, horario, medicoId);
    }

    @Override
    public void verifyThrows(final PedidoAgendamentoDTO request){
        PreconditionFailedException exception = assertThrows(PreconditionFailedException.class,
                () -> super.getValidation().validate(request));
        String expectedExceptionMessage = "O horário de agendamento deve estar entre 9h e 17h";
        String actualExceptionMessage = exception.getErrorMessage().getMessage();
        assertEquals(expectedExceptionMessage, actualExceptionMessage);
    }
}
