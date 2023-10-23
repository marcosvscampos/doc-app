package org.roguesoft.docapp.application.api;

import lombok.RequiredArgsConstructor;
import org.roguesoft.docapp.application.dto.agendamento.AgendamentoDTO;
import org.roguesoft.docapp.application.dto.agendamento.PedidoAgendamentoDTO;
import org.roguesoft.docapp.application.dto.ResponseDTO;
import org.roguesoft.docapp.application.dto.filter.AgendamentoFilter;
import org.roguesoft.docapp.domain.service.agendamento.AgendamentoDomainService;
import org.roguesoft.docapp.domain.service.DomainService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AgendamentoController {

    private final DomainService<PedidoAgendamentoDTO> pedidoAgendamentoService;

    private final AgendamentoDomainService agendamentoService;

    //Buscar todos os horarios agendamentos de um médico dado uma data
    @GetMapping(value = "/medicos/{cod_medico}/agendamentos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AgendamentoDTO>> recuperaAgendaMedico(final AgendamentoFilter agendamentoFilter,
                                                                     @PathVariable(name = "cod_medico") final String medicoId){
        List<AgendamentoDTO> medicoAgendamentos = agendamentoService.buscarAgendamentosPorId(agendamentoFilter, medicoId);
        return ResponseEntity.ok(medicoAgendamentos);
    }

    //Buscar todos os horarios agendamentos de um paciente dado uma data
    @GetMapping(value = "/pacientes/{cod_paciente}/agendamentos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AgendamentoDTO>> recuperaAgendaPaciente(final AgendamentoFilter agendamentoFilter,
                                                       @PathVariable(name = "cod_paciente") final String pacienteId){
        List<AgendamentoDTO> pacienteAgendamentos = agendamentoService.buscarAgendamentosPorId(agendamentoFilter, pacienteId);
        return ResponseEntity.ok(pacienteAgendamentos);
    }

    @PostMapping(value = "/agendamentos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> criarAgendamentoPaciente(@RequestBody final PedidoAgendamentoDTO request){
        return ResponseEntity.ok(pedidoAgendamentoService.create(request));
    }

}
