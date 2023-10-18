package org.roguesoft.docapp.application.dto.filter;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class Filter {

    private Integer page;

    private Integer size;

    public Filter(){
        this.page = 0;
        this.size = 10;
    }

}
