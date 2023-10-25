package org.roguesoft.docapp.helper;

import com.roguesoft.apiexception.exception.PreconditionFailedException;
import org.roguesoft.docapp.application.dto.agendamento.PedidoAgendamentoDTO;
import org.roguesoft.docapp.domain.validation.pedidoagendamento.impl.DataAgendamentoFeriadoValidation;
import org.roguesoft.docapp.infrastructure.repository.FeriadoRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DataAgendamentoFeriadoValidationTestHelper extends PedidoAgendamentoValidationTestHelper {

    public DataAgendamentoFeriadoValidationTestHelper(final FeriadoRepository repository){
        super(new DataAgendamentoFeriadoValidation(repository));
    }

    @Override
    public PedidoAgendamentoDTO prepareRequest(String data){
        String horario = "09:00";
        String medicoId = "MED-123456789-ABC";
        return super.getRequestBuilder().buildValid(data, horario, medicoId);
    }

    @Override
    public void verifyThrows(final PedidoAgendamentoDTO request){
        PreconditionFailedException exception = assertThrows(PreconditionFailedException.class,
                () -> super.getValidation().validate(request));
        String expectedExceptionMessage = "Agendamento não pode ser realizado em dias de feriado: " +  request.getData() + " - Feriado de teste";
        String actualExceptionMessage = exception.getErrorMessage().getMessage();
        assertEquals(expectedExceptionMessage, actualExceptionMessage);
    }
}
