package org.roguesoft.docapp.application.dto.filter;

import lombok.Data;

@Data
public class ConvenioFilter extends IndividuoFilter {

    private String cnpj;

    public ConvenioFilter(){
        super();
    }
}
