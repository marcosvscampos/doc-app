package org.roguesoft.docapp.validation.pedidoagendamento;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.roguesoft.docapp.application.dto.agendamento.PedidoAgendamentoDTO;
import org.roguesoft.docapp.helper.DataAgendamentoFeriadoValidationTestHelper;
import org.roguesoft.docapp.helper.PedidoAgendamentoValidationTestHelper;
import org.roguesoft.docapp.infrastructure.model.Feriado;
import org.roguesoft.docapp.infrastructure.repository.FeriadoRepository;
import org.roguesoft.docapp.utils.EntityBuilder;
import org.roguesoft.docapp.utils.entity.FeriadoEntityBuilder;

import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DataAgendamentoFeriadoValidationTest {

    private PedidoAgendamentoValidationTestHelper helper;

    private EntityBuilder<Optional<Feriado>> entityBuilder;

    @Mock
    private FeriadoRepository repository;

    @BeforeEach
    public void setUp(){
        this.helper = new DataAgendamentoFeriadoValidationTestHelper(this.repository);
        this.entityBuilder =  new FeriadoEntityBuilder();
    }

    @Test
    void testPedidoAgendamentoDiaComumSuccessful(){
         PedidoAgendamentoDTO request = this.helper.prepareRequest("18/01/2024");

         doReturn(Optional.empty()).when(this.repository).findByDiaAndMes(18, 1);

         this.helper.getValidation().validate(request);

         verify(this.repository, times(1)).findByDiaAndMes(18, 1);
    }

    @Test
    void testPedidoAgendamentoDiaFeriadoMustFail(){
        PedidoAgendamentoDTO request = this.helper.prepareRequest("07/09/2025");

        Optional<Feriado> sampleFeriado = entityBuilder.build("07", "09");
        doReturn(sampleFeriado).when(this.repository).findByDiaAndMes(7, 9);

        this.helper.verifyThrows(request);
    }
}
