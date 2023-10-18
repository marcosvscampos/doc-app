package org.roguesoft.docapp.application.dto.filter;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PacienteFilter extends IndividuoFilter {

    private String rg;

    private String cpf;

    PacienteFilter(){
        super();
    }

}
