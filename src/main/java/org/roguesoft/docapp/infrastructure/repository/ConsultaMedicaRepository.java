package org.roguesoft.docapp.infrastructure.repository;

import org.roguesoft.docapp.infrastructure.model.ConsultaMedica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ConsultaMedicaRepository extends JpaRepository<ConsultaMedica, String> {

    @Query(value = "SELECT cm FROM ConsultaMedica cm" +
            " WHERE cm.data = :data" +
            " AND cm.medico.id = :medicoId")
    List<ConsultaMedica> findByDataAndMedicoId(final Date data, final String medicoId);

    @Query(value = "SELECT cm FROM ConsultaMedica cm" +
            " WHERE cm.data = :data" +
            " AND cm.paciente.id = :pacienteId" +
            " AND cm.medico.id = :medicoId")
    List<ConsultaMedica> findByDataByPacienteIdAndMedicoId(final Date data, final String pacienteId, final String medicoId);
    
}
