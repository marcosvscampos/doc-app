package org.roguesoft.docapp.validation.pedidoagendamento;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.roguesoft.docapp.application.dto.agendamento.PedidoAgendamentoDTO;
import org.roguesoft.docapp.helper.DataAgendamentoDiasAntecedenciaValidationTestHelper;
import org.roguesoft.docapp.helper.PedidoAgendamentoValidationTestHelper;

@ExtendWith(MockitoExtension.class)
class DataAgendamentoDiasAntecedenciaValidationTest {

    private PedidoAgendamentoValidationTestHelper helper;

    @BeforeEach
    public void setUp(){
        this.helper = new DataAgendamentoDiasAntecedenciaValidationTestHelper();
    }

    @Test
    void testDataAgendamentoComMaisTresDiasAntecedenciaSuccessful(){
        PedidoAgendamentoDTO request = this.helper.prepareRequest("31/10/2024");
        this.helper.getValidation().validate(request);
    }

    @Test
    void testDataAgendamentoMenosTresDiasAntecedenciaMustFail(){
        PedidoAgendamentoDTO request = this.helper.prepareRequest("25/10/2023");
        this.helper.verifyThrows(request);
    }

}
