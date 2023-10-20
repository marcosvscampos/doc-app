package org.roguesoft.docapp.infrastructure.model.queries;

public class FindAgendaMedicoQuery {

    private FindAgendaMedicoQuery(){}

    public static final String QUERY_AGENDA_MEDICO = "SELECT tcm.cod_consulta," +
            " tm.cod_medico," +
            " tcm.cod_individuo," +
            " tm.nome," +
            " tm.especialidade," +
            " tcm.`data`," +
            " tcm.horario," +
            " tcm.status" +
            " FROM TB_MEDICOS tm" +
            " LEFT JOIN TB_CONSULTAS_MEDICAS tcm" +
            " ON tm.cod_medico = tcm.cod_medico" +
            " WHERE tcm.cod_individuo IS NOT NULL" +
            " AND tm.cod_medico = :medicoId" +
            " AND tcm.`data` = :data" +
            " ORDER BY tcm.horario";
}
