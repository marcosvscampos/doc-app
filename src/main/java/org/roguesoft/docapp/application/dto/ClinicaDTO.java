package org.roguesoft.docapp.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode
public class ClinicaDTO implements Serializable {

    @JsonProperty("cod_clinica")
    private String id;

    private String nome;

    private String telefone;

    private String email;

    @JsonProperty("cod_convenio_gerenciador")
    private String convenioId;

    @Builder(builderMethodName = "clinicaBuilder")
    public ClinicaDTO(final String id, final String nome,
                      final String telefone, final String email,
                      final ConvenioDTO convenio){
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.convenioId = convenio.getId();
    }
}
