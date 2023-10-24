package org.roguesoft.docapp.application.dto.filter;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.roguesoft.docapp.infrastructure.utils.DateParser;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class AgendamentoFilter extends Filter {

    private String data;

    public AgendamentoFilter(){
        super();
    }

    public Date parseData(){
        return DateParser.parse(this.data, "dd/MM/yyyy");
    }

}
