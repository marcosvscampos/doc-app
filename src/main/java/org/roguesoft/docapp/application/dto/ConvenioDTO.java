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
public class ConvenioDTO extends IndividuoDTO {

    @JsonProperty("cod_convenio")
    private String id;
    private String cnpj;

    @Builder(builderMethodName = "convenioBuilder")
    public ConvenioDTO(final String id, final String cnpj,
                       final IndividuoDTO individuo){
        this.id = id;
        this.cnpj = cnpj;
        super.setNome(individuo.getNome());
        super.setTelefone(individuo.getTelefone());
        super.setEndereco(individuo.getEndereco());
    }

}
