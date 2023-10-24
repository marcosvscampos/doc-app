package org.roguesoft.docapp.domain.validation.pedidoagendamento.factory;

import lombok.RequiredArgsConstructor;
import org.roguesoft.docapp.domain.validation.pedidoagendamento.PedidoAgendamentoValidation;
import org.roguesoft.docapp.domain.validation.pedidoagendamento.impl.ConflitoHorarioExistenteValidation;
import org.roguesoft.docapp.domain.validation.pedidoagendamento.impl.DataAgendamentoDiaUtilValidation;
import org.roguesoft.docapp.domain.validation.pedidoagendamento.impl.DataAgendamentoDiasAntecedenciaValidation;
import org.roguesoft.docapp.domain.validation.pedidoagendamento.impl.DataAgendamentoFeriadoValidation;
import org.roguesoft.docapp.domain.validation.pedidoagendamento.impl.HorarioAgendamentoLimiteValidation;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PedidoAgendamentoValidationFactory {

    private final HorarioAgendamentoLimiteValidation horarioAgendamentoLimiteValidation;

    private final DataAgendamentoDiasAntecedenciaValidation diasAntecedenciaValidation;

    private final DataAgendamentoDiaUtilValidation diaUtilValidation;

    private final ConflitoHorarioExistenteValidation conflitoHorarioExistenteValidation;

    private final DataAgendamentoFeriadoValidation dataAgendamentoFeriadoValidation;

    public List<PedidoAgendamentoValidation> getInstance(){
        return Arrays.asList(horarioAgendamentoLimiteValidation,
                diasAntecedenciaValidation,
                diaUtilValidation,
                conflitoHorarioExistenteValidation,
                dataAgendamentoFeriadoValidation);
    }
}
