package org.roguesoft.docapp.application.dto.filter;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ConvenioFilter extends IndividuoFilter {

    private String cnpj;

    public ConvenioFilter(){
        super();
    }
}
