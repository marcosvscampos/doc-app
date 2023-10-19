package org.roguesoft.docapp.application.api;

import lombok.RequiredArgsConstructor;
import org.roguesoft.docapp.application.dto.ClinicaDTO;
import org.roguesoft.docapp.application.dto.ResponseDTO;
import org.roguesoft.docapp.application.dto.filter.ClinicaFilter;
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
@RequestMapping("/clinicas")
@RequiredArgsConstructor
public class ClinicaController {

    private final DomainService<ClinicaDTO> clinicaService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> cadastrarClinica(@RequestBody final ClinicaDTO request){
        return ResponseEntity.ok(clinicaService.create(request));
    }

    @GetMapping(value = "/{cod_clinica}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClinicaDTO> recuperarConvenioPorId(@PathVariable(value = "cod_clinica") final String id){
        return ResponseEntity.ok(clinicaService.findById(id));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ClinicaDTO>> recuperarClinicasPorFiltros(final ClinicaFilter filter){
        return ResponseEntity.ok(clinicaService.findAll(filter));
    }
}
