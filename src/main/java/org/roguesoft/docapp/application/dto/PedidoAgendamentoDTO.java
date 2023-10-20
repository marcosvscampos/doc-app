package org.roguesoft.docapp.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PedidoAgendamentoDTO implements Serializable {

    @JsonProperty("nome_paciente")
    private String nome;

    @JsonProperty("data_nascimento")
    private String dataNascimento;

    @JsonProperty("data")
    private String data;

    private String horario;

    @JsonProperty("cod_medico")
    private String medicoId;

    public Date parseDataNascimento(){
        Date parsedDate = this.parseDate(this.dataNascimento, "dd/MM/yyyy");
        return new Date(parsedDate.getTime());
    }

    public Date parseData(){
        Date parsedDate = this.parseDate(this.data, "dd/MM/yyyy");
        return new Date(parsedDate.getTime());
    }

    public Time parseHorario(){
        Date parsedHour = this.parseDate(this.horario, "HH:mm");
        return new Time(parsedHour.getTime());
    }

    private Date parseDate(final String value, final String format){
        try {
            if (Objects.nonNull(value) && !value.isEmpty()) {
                SimpleDateFormat dateFormat = new SimpleDateFormat(format);
                return dateFormat.parse(value);
            }
            throw new RuntimeException("Não é possivel converter uma data/hora vazia");
        } catch (ParseException pe){
            throw new RuntimeException("Erro ao converter data/hora: " + value);
        }
    }

}
