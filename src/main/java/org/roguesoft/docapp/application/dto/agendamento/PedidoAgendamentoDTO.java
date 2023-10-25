package org.roguesoft.docapp.application.dto.agendamento;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.roguesoft.docapp.infrastructure.utils.DateParser;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class PedidoAgendamentoDTO implements Serializable {

    @JsonProperty("nome_paciente")
    private String nome;

    @JsonProperty("data_nascimento")
    private String dataNascimento;

    @JsonProperty("data_consulta")
    private String data;

    @JsonProperty("horario_consulta")
    private String horario;

    @JsonProperty("cod_medico")
    private String medicoId;

    public Date parseDataNascimento(){
        Date parsedDate = DateParser.parse(this.dataNascimento, "dd/MM/yyyy");
        return new Date(parsedDate.getTime());
    }

    public Date parseData(){
        Date parsedDate = DateParser.parse(this.data, "dd/MM/yyyy");
        return new Date(parsedDate.getTime());
    }

    public Time parseHorario(){
        Date parsedHour = DateParser.parse(this.horario, "HH:mm");
        return new Time(parsedHour.getTime());
    }

}
