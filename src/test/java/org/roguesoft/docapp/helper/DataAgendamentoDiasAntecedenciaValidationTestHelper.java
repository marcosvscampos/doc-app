package org.roguesoft.docapp.helper;

import com.roguesoft.apiexception.exception.PreconditionFailedException;
import org.roguesoft.docapp.application.dto.agendamento.PedidoAgendamentoDTO;
import org.roguesoft.docapp.domain.validation.pedidoagendamento.impl.DataAgendamentoDiasAntecedenciaValidation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DataAgendamentoDiasAntecedenciaValidationTestHelper extends PedidoAgendamentoValidationTestHelper {

    public DataAgendamentoDiasAntecedenciaValidationTestHelper(){
        super(new DataAgendamentoDiasAntecedenciaValidation());
    }

    @Override
    public PedidoAgendamentoDTO prepareRequest(String data){
        String horario = "08:00";
        String medicoId = "MED-123456789-ABC";
        return this.getRequestBuilder().buildValid(data, horario, medicoId);
    }

    @Override
    public void verifyThrows(final PedidoAgendamentoDTO request){
        PreconditionFailedException exception = assertThrows(PreconditionFailedException.class,
                () -> super.getValidation().validate(request));
        String expectedExceptionMessage = "Data de agendamento deve ser a partir de 3 dias da data atual";
        String actualExceptionMessage = exception.getErrorMessage().getMessage();
        assertEquals(expectedExceptionMessage, actualExceptionMessage);
    }
}
