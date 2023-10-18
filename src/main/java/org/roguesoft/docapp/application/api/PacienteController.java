package org.roguesoft.docapp.application.api;

import lombok.RequiredArgsConstructor;
import org.roguesoft.docapp.application.dto.PacienteDTO;
import org.roguesoft.docapp.application.dto.ResponseDTO;
import org.roguesoft.docapp.application.dto.filter.PacienteFilter;
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
@RequestMapping("/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final DomainService<PacienteDTO> pacienteService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> cadastrarPaciente(@RequestBody final PacienteDTO request){
        ResponseDTO response = pacienteService.create(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{cod_paciente}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PacienteDTO> recuperarPacientePorId(@PathVariable(name = "cod_paciente")final String id){
        return ResponseEntity.ok(pacienteService.findById(id));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<PacienteDTO>> recuperarPacientesPorFiltros(final PacienteFilter filter){
        return ResponseEntity.ok(pacienteService.findAll(filter));
    }

}
