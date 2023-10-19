package org.roguesoft.docapp.application.dto.filter;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ClinicaFilter extends Filter {

    private String nome;

    public ClinicaFilter(){
        super();
    }

}
