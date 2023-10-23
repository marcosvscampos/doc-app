package org.roguesoft.docapp.application.dto.agendamento;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"cod_consulta", "data", "horario", "status", "cod_medico", "medico", "especialidade", "cod_paciente", "paciente"})
public class AgendamentoDTO implements Serializable {

    @JsonProperty("cod_consulta")
    private String consultaId;

    @JsonProperty("cod_medico")
    private String medicoId;

    @JsonProperty("medico")
    private String nomeMedico;

    private String especialidade;

    private String data;

    private String horario;

    private String status;

    @JsonProperty("cod_paciente")
    private String pacienteId;

    @JsonProperty("paciente")
    private String nomePaciente;

}
