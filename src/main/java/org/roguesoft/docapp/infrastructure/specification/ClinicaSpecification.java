package org.roguesoft.docapp.infrastructure.specification;

import org.roguesoft.docapp.infrastructure.model.Clinica;
import org.springframework.data.jpa.domain.Specification;

public class ClinicaSpecification {

    private ClinicaSpecification(){}

    public static Specification<Clinica> nome(String nome){
        return (root, cq, cb) ->
                cb.like(root.get("nome"), "%" + nome + "%");
    }

}
