package org.roguesoft.docapp.infrastructure.specification;

import org.roguesoft.docapp.infrastructure.model.Medico;
import org.springframework.data.jpa.domain.Specification;

public class MedicoSpecification {

    private MedicoSpecification(){}

    public static Specification<Medico> nome(String nome){
        return (root, cq, cb) ->
                cb.like(root.get("nome"), "%" + nome + "%");
    }

    public static Specification<Medico> especialidade(String especialidade){
        return (root, cq, cb) ->
                cb.equal(root.get("especialidade"), especialidade);
    }

}
