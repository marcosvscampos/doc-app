package org.roguesoft.docapp.application.dto.filter;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MedicoFilter extends Filter {

    private String nome;

    private String especialidade;

    public MedicoFilter(){
        super();
    }

}
