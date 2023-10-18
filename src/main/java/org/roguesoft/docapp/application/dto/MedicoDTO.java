package org.roguesoft.docapp.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MedicoDTO implements Serializable {

    @JsonProperty("cod_medico")
    private String id;

    private String nome;

    private String especialidade;

    @JsonProperty("cod_clinica")
    private String clinicaId;

    @Builder(builderMethodName = "medicoBuilder")
    public MedicoDTO(final String id, final String nome,
                     final String especialidade, final ClinicaDTO clinica){
        this.id = id;
        this.nome = nome;
        this.especialidade = especialidade;
        this.clinicaId = clinica.getId();
    }
}
