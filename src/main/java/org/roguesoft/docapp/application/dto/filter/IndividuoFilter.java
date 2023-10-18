package org.roguesoft.docapp.application.dto.filter;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class IndividuoFilter extends Filter {

    private String nome;

    public IndividuoFilter(){
        super();
    }

}
