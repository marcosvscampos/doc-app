package org.roguesoft.docapp.helper;

import lombok.Getter;
import org.roguesoft.docapp.application.dto.agendamento.PedidoAgendamentoDTO;
import org.roguesoft.docapp.domain.validation.pedidoagendamento.PedidoAgendamentoValidation;
import org.roguesoft.docapp.utils.RequestBuilder;
import org.roguesoft.docapp.utils.request.PedidoAgendamentoRequestBuilder;

@Getter
public abstract class PedidoAgendamentoValidationTestHelper {

    private final RequestBuilder<PedidoAgendamentoDTO> requestBuilder;

    private final PedidoAgendamentoValidation validation;

    public PedidoAgendamentoValidationTestHelper(final PedidoAgendamentoValidation validation){
        this.validation = validation;
        this.requestBuilder = new PedidoAgendamentoRequestBuilder();
    }

    public abstract PedidoAgendamentoDTO prepareRequest(String value);

    public abstract void verifyThrows(final PedidoAgendamentoDTO request);

}
