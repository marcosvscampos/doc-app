package org.roguesoft.docapp.infrastructure.specification;

import org.roguesoft.docapp.infrastructure.model.Convenio;
import org.springframework.data.jpa.domain.Specification;

public class ConvenioSpecification {

    private ConvenioSpecification(){}

    public static Specification<Convenio> cnpj(String cnpj){
        return (root, cq, cb) ->
                cb.like(root.get("cnpj"), "%" + cnpj + "%");
    }

}
