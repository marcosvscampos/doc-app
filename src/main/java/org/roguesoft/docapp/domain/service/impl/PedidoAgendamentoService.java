package org.roguesoft.docapp.domain.service.impl;

import org.roguesoft.docapp.application.dto.PedidoAgendamentoDTO;
import org.roguesoft.docapp.application.dto.ResponseDTO;
import org.roguesoft.docapp.application.dto.filter.Filter;
import org.roguesoft.docapp.domain.mapper.PedidoAgendamentoMapper;
import org.roguesoft.docapp.domain.mapper.impl.entity.ConsultaMedicaMapper;
import org.roguesoft.docapp.domain.service.DomainService;
import org.roguesoft.docapp.infrastructure.model.ConsultaMedica;
import org.roguesoft.docapp.infrastructure.model.Medico;
import org.roguesoft.docapp.infrastructure.model.Paciente;
import org.roguesoft.docapp.infrastructure.repository.ConsultaMedicaRepository;
import org.roguesoft.docapp.infrastructure.repository.MedicoRepository;
import org.roguesoft.docapp.infrastructure.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class PedidoAgendamentoService implements DomainService<PedidoAgendamentoDTO> {

    private final PacienteRepository pacienteRepository;

    private final PedidoAgendamentoMapper pedidoAgendamentoMapper;

    private final ConsultaMedicaRepository consultaMedicaRepository;

    private final MedicoRepository medicoRepository;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    private static final String PATH_NAME = "consultas";

    public PedidoAgendamentoService(final PacienteRepository pacienteRepository,
                                    final PedidoAgendamentoMapper pedidoAgendamentoMapper,
                                    final ConsultaMedicaRepository consultaMedicaRepository,
                                    final MedicoRepository medicoRepository){
        this.pacienteRepository = pacienteRepository;
        this.pedidoAgendamentoMapper = pedidoAgendamentoMapper;
        this.consultaMedicaRepository = consultaMedicaRepository;
        this.medicoRepository = medicoRepository;
    }

    @Override
    @Transactional
    public ResponseDTO create(final PedidoAgendamentoDTO request) {

        Medico medico = medicoRepository.findById(request.getMedicoId())
                .orElseThrow(() -> new RuntimeException("Não foram encontrados médicos com ID: " + request.getMedicoId()));

        Date dataNascimento = request.parseDataNascimento();
        Paciente pacienteAgendamento = pacienteRepository.findByNomeAndDataNascimento(request.getNome(), dataNascimento)
                .orElseGet(() -> pacienteRepository.save(pedidoAgendamentoMapper.toPacienteAgendamento(request)));

        ConsultaMedica consultaMedica = ConsultaMedicaMapper.fromPedidoAgendamento(request);
        consultaMedica.setMedico(medico);
        consultaMedica.setPaciente(pacienteAgendamento);

        ConsultaMedica savedConsulta = consultaMedicaRepository.save(consultaMedica);

        return new ResponseDTO(contextPath, PATH_NAME, savedConsulta.getId());
    }

    @Override
    public PedidoAgendamentoDTO findById(String id) {
        return null;
    }

    @Override
    public Page<PedidoAgendamentoDTO> findAll(Filter filter) {
        return null;
    }
}
