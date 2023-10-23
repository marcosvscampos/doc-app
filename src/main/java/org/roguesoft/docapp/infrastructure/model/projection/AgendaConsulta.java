package org.roguesoft.docapp.infrastructure.model.projection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AgendaConsulta {

    @Id
    @Column(name = "cod_consulta")
    private String consultaId;

    @Column(name = "cod_medico")
    private String medicoId;

    @Column(name = "nome_medico")
    private String nomeMedico;

    @Column(name = "especialidade")
    private String especialidade;

    @Column(name = "cod_individuo")
    private String pacienteId;

    @Column(name = "nome_paciente")
    private String nomePaciente;

    @Temporal(TemporalType.DATE)
    @Column(name = "data")
    private Date data;

    @Temporal(TemporalType.TIME)
    @Column(name = "horario")
    private Time horario;

    @Column(name = "status")
    private String status;
}
