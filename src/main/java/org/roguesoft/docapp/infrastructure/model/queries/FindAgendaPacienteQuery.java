package org.roguesoft.docapp.infrastructure.model.queries;

public class FindAgendaPacienteQuery {

    private FindAgendaPacienteQuery(){}

    public static final String QUERY_AGENDA_PACIENTE = "SELECT" +
            " tcm.cod_consulta," +
            " tcm.cod_medico," +
            " tm.nome as nome_medico," +
            " tm.especialidade," +
            " tcm.cod_individuo," +
            " ti.nome as nome_paciente," +
            " tcm.`data`," +
            " tcm.horario," +
            " tcm.status" +
            " FROM TB_MEDICOS tm" +
            " LEFT JOIN TB_CONSULTAS_MEDICAS tcm ON tm.cod_medico = tcm.cod_medico" +
            " LEFT JOIN TB_INDIVIDUOS ti ON ti.cod_individuo = tcm.cod_individuo" +
            " WHERE tcm.cod_individuo = :pacienteId" +
            " AND tcm.`data` = :data" +
            " ORDER BY tcm.horario";

}
