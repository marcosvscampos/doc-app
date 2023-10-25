package org.roguesoft.docapp.validation.pedidoagendamento;

import com.roguesoft.apiexception.exception.PreconditionFailedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.roguesoft.docapp.application.dto.agendamento.PedidoAgendamentoDTO;
import org.roguesoft.docapp.domain.validation.pedidoagendamento.PedidoAgendamentoValidation;
import org.roguesoft.docapp.domain.validation.pedidoagendamento.impl.DataAgendamentoFeriadoValidation;
import org.roguesoft.docapp.infrastructure.model.Feriado;
import org.roguesoft.docapp.infrastructure.repository.FeriadoRepository;
import org.roguesoft.docapp.utils.EntityBuilder;
import org.roguesoft.docapp.utils.RequestBuilder;
import org.roguesoft.docapp.utils.entity.FeriadoEntityBuilder;
import org.roguesoft.docapp.utils.request.PedidoAgendamentoRequestBuilder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DataAgendamentoFeriadoValidationTest {

    private PedidoAgendamentoValidation validation;

    private RequestBuilder<PedidoAgendamentoDTO> requestBuilder;

    private EntityBuilder<Optional<Feriado>> entityBuilder;

    @Mock
    private FeriadoRepository repository;

    @BeforeEach
    public void setUp(){
        this.validation = new DataAgendamentoFeriadoValidation(this.repository);
        this.requestBuilder = new PedidoAgendamentoRequestBuilder();
        this.entityBuilder =  new FeriadoEntityBuilder();
    }

    @Test
    void testPedidoAgendamentoDiaComumSuccessful(){
         PedidoAgendamentoDTO request = this.prepareRequest("18/01/2024");

         doReturn(Optional.empty()).when(this.repository).findByDiaAndMes(18, 1);

         this.validation.validate(request);

         verify(this.repository, times(1)).findByDiaAndMes(18, 1);
    }

    @Test
    void testPedidoAgendamentoDiaFeriadoMustFail(){
        PedidoAgendamentoDTO request = this.prepareRequest("07/09/2025");

        Optional<Feriado> sampleFeriado = entityBuilder.build("07", "09");
        doReturn(sampleFeriado).when(this.repository).findByDiaAndMes(7, 9);

        this.verifyThrows(request, sampleFeriado.get());
    }

    private PedidoAgendamentoDTO prepareRequest(String data){
        String horario = "09:00";
        String medicoId = "MED-123456789-ABC";
        return this.requestBuilder.buildValid(data, horario, medicoId);
    }

    private void verifyThrows(final PedidoAgendamentoDTO request, final Feriado feriado){
        PreconditionFailedException exception = assertThrows(PreconditionFailedException.class,
                () -> validation.validate(request));
        String expectedExceptionMessage = "Agendamento não pode ser realizado em dias de feriado: " +  request.getData() + " - " + feriado.getDescription();
        String actualExceptionMessage = exception.getErrorMessage().getMessage();
        assertEquals(expectedExceptionMessage, actualExceptionMessage);
    }
}
