package org.roguesoft.docapp.domain.mapper.impl.entity;

import org.roguesoft.docapp.application.dto.EnderecoDTO;
import org.roguesoft.docapp.application.dto.IndividuoDTO;
import org.roguesoft.docapp.infrastructure.model.Individuo;

import java.util.Objects;

public class EnderecoDomainMapper {

    private EnderecoDomainMapper(){}

    public static void toModel(final Individuo model, final EnderecoDTO endereco){
        model.setRua(endereco.getRua());
        model.setBairro(endereco.getBairro());
        model.setNumero(Objects.isNull(endereco.getNumero()) ? "S/N" : String.valueOf(endereco.getNumero()));
        model.setCidade(endereco.getCidade());
        model.setCep(endereco.getCep());
        model.setEstado(endereco.getEstado());
    }

    public static void toDto(final IndividuoDTO dto, Individuo model){
        EnderecoDTO endereco = new EnderecoDTO();
        endereco.setBairro(model.getBairro());
        endereco.setRua(model.getRua());
        endereco.setNumero(Objects.nonNull(model.getNumero()) && !model.getNumero().equals("S/N") ? Integer.parseInt(model.getNumero()) : 0);
        endereco.setCidade(model.getCidade());
        endereco.setCep(model.getCep());
        endereco.setEstado(model.getEstado());
        dto.setEndereco(endereco);
    }

}
