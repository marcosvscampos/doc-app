package org.roguesoft.docapp.application.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
public class PacienteDTO extends IndividuoDTO {

    @JsonProperty("cod_paciente")
    private String id;
    private String cpf;
    private String rg;

    @Builder(builderMethodName = "pacienteBuilder")
    public PacienteDTO(final String id, final String cpf, final String rg,
                       final IndividuoDTO individuo){
        this.id = id;
        this.cpf = cpf;
        this.rg = rg;
        super.setNome(individuo.getNome());
        super.setTelefone(individuo.getTelefone());
        super.setEndereco(individuo.getEndereco());
    }

}
