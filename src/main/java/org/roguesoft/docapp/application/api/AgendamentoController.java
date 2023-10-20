package org.roguesoft.docapp.application.api;

import lombok.RequiredArgsConstructor;
import org.roguesoft.docapp.application.dto.PedidoAgendamentoDTO;
import org.roguesoft.docapp.application.dto.ResponseDTO;
import org.roguesoft.docapp.domain.service.DomainService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AgendamentoController {

    private final DomainService<PedidoAgendamentoDTO> pedidoAgendamentoService;

    @GetMapping(value = "/medicos/{cod_medico}/agendamentos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> recuperaAgendaMedico(){
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/pacientes/{cod_paciente}/agendamentos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> recuperaAgendaCliente(){
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/agendamentos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> criarAgendamentoPaciente(@RequestBody final PedidoAgendamentoDTO request){
        return ResponseEntity.ok(pedidoAgendamentoService.create(request));
    }

}
