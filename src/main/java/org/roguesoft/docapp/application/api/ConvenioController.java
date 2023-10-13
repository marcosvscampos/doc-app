package org.roguesoft.docapp.application.api;

import lombok.RequiredArgsConstructor;
import org.roguesoft.docapp.application.dto.ConvenioDTO;
import org.roguesoft.docapp.application.dto.ResponseDTO;
import org.roguesoft.docapp.domain.service.DomainService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/convenios")
@RequiredArgsConstructor
public class ConvenioController {

    private final DomainService<ConvenioDTO> convenioService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> cadastrarConvenio(@RequestBody final ConvenioDTO request){
        ResponseDTO response = convenioService.create(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{cod_convenio}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConvenioDTO> recuperarConvenioPorId(@PathVariable(name = "cod_convenio")final String id){
        return ResponseEntity.ok(convenioService.findById(id));
    }

}
