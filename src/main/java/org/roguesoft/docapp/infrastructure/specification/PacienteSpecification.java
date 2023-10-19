package org.roguesoft.docapp.infrastructure.specification;

import org.roguesoft.docapp.infrastructure.model.Paciente;
import org.springframework.data.jpa.domain.Specification;

public class PacienteSpecification {

    private PacienteSpecification(){}

    public static Specification<Paciente> cpf(String cpf){
        return (root, cq, cb) ->
                cb.like(root.get("cpf"), "%" + cpf + "%");
    }

    public static Specification<Paciente> rg(String rg) {
        return (root, cq, cb) ->
                cb.like(root.get("rg"), "%" + rg + "%");
    }

}
