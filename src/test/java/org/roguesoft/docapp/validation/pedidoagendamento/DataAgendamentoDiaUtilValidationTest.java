package org.roguesoft.docapp.validation.pedidoagendamento;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.roguesoft.docapp.application.dto.agendamento.PedidoAgendamentoDTO;
import org.roguesoft.docapp.helper.DataAgendamentoDiaUtilValidationTestHelper;
import org.roguesoft.docapp.helper.PedidoAgendamentoValidationTestHelper;

@ExtendWith(MockitoExtension.class)
class DataAgendamentoDiaUtilValidationTest {

    private PedidoAgendamentoValidationTestHelper helper;

    @BeforeEach
    public void setUp(){
        this.helper = new DataAgendamentoDiaUtilValidationTestHelper();
    }

    @Test
    void testDataAgendamentoDiaUtilSuccessful(){
        PedidoAgendamentoDTO request = this.helper.prepareRequest("01/11/2023");
        this.helper.getValidation().validate(request);
    }

    @Test
    void testDataAgendamentoSabadoMustFail(){
        PedidoAgendamentoDTO request = this.helper.prepareRequest("28/10/2023");
        this.helper.verifyThrows(request);
    }

    @Test
    void testDataAgendamentoDomingoMustFail(){
        PedidoAgendamentoDTO request = this.helper.prepareRequest("29/10/2023");
        this.helper.verifyThrows(request);
    }
}
