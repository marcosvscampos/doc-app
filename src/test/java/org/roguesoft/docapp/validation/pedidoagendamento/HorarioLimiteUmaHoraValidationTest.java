package org.roguesoft.docapp.validation.pedidoagendamento;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.roguesoft.docapp.application.dto.agendamento.PedidoAgendamentoDTO;
import org.roguesoft.docapp.helper.HorarioLimiteUmaHoraValidationHelper;
import org.roguesoft.docapp.helper.PedidoAgendamentoValidationTestHelper;

@ExtendWith(MockitoExtension.class)
class HorarioLimiteUmaHoraValidationTest {

    private PedidoAgendamentoValidationTestHelper helper;

    @BeforeEach
    public void setUp(){
        this.helper = new HorarioLimiteUmaHoraValidationHelper();

    }

    @Test
    void testPedidoAgendamentoHorarioCorretoSuccessful(){
        PedidoAgendamentoDTO request = this.helper.prepareRequest("10:00");
        this.helper.getValidation().validate(request);
    }

    @Test
    void testPedidoAgendamentoHorarioIncorretoMustFail(){
        PedidoAgendamentoDTO request = this.helper.prepareRequest("11:20");
        this.helper.verifyThrows(request);
    }

}
