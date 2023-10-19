package org.roguesoft.docapp.application.api;

import lombok.RequiredArgsConstructor;
import org.roguesoft.docapp.application.dto.MedicoDTO;
import org.roguesoft.docapp.application.dto.ResponseDTO;
import org.roguesoft.docapp.application.dto.filter.MedicoFilter;
import org.roguesoft.docapp.domain.service.DomainService;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
@RequiredArgsConstructor
public class MedicoController {

    private final DomainService<MedicoDTO> medicoService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> cadastrarMedico(@RequestBody final MedicoDTO request){
        ResponseDTO response = medicoService.create(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{cod_medico}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MedicoDTO> recuperarMedicoPorId(@PathVariable(name = "cod_medico")final String id){
        return ResponseEntity.ok(medicoService.findById(id));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<MedicoDTO>> recuperarMedicosPorFiltros(final MedicoFilter filter){
        return ResponseEntity.ok(medicoService.findAll(filter));
    }

}
