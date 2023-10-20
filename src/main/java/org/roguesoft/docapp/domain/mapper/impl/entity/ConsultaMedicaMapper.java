package org.roguesoft.docapp.domain.mapper.impl.entity;

import org.roguesoft.docapp.application.dto.PedidoAgendamentoDTO;
import org.roguesoft.docapp.infrastructure.model.ConsultaMedica;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

public class ConsultaMedicaMapper  {

    public static ConsultaMedica fromPedidoAgendamento(final PedidoAgendamentoDTO request){
        Date data = request.parseData();
        Time horario = request.parseHorario();

        ConsultaMedica consultaMedica = new ConsultaMedica();
        consultaMedica.setDate(data);
        consultaMedica.setHorario(horario);
        consultaMedica.setStatus("PENDENTE");
        consultaMedica.setValor(BigDecimal.ZERO);
        return consultaMedica;
    }
}
