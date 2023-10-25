package org.roguesoft.docapp.validation.pedidoagendamento;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.roguesoft.docapp.application.dto.agendamento.PedidoAgendamentoDTO;
import org.roguesoft.docapp.helper.HorarioAgendamentoLimiteValidationTestHelper;
import org.roguesoft.docapp.helper.PedidoAgendamentoValidationTestHelper;

@ExtendWith(MockitoExtension.class)
class HorarioAgendamentoLimiteValidationTest {

    private PedidoAgendamentoValidationTestHelper helper;

    @BeforeEach
    public void setUp(){
        this.helper = new HorarioAgendamentoLimiteValidationTestHelper();
    }

    @Test
    void testPedidoAgendamentoDentroHorarioComercialSuccessful(){
        PedidoAgendamentoDTO request = this.helper.prepareRequest("09:00");
        this.helper.getValidation().validate(request);
    }

    @Test
    void testPedidoAgendamentoAntesHorarioPermitidoMustFail(){
        PedidoAgendamentoDTO request = this.helper.prepareRequest("06:00");
        this.helper.verifyThrows(request);
    }

    @Test
    void testPedidoAgendamentoDepoisHorarioPermitidoMustFail(){
        PedidoAgendamentoDTO request = this.helper.prepareRequest("19:00");
        this.helper.verifyThrows(request);
    }

}
