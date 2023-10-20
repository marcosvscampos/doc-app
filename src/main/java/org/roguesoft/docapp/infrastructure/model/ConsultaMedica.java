package org.roguesoft.docapp.infrastructure.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.roguesoft.docapp.infrastructure.model.sequence.CustomSequenceIdGenerator;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

@Data
@Entity
@Table(name = "TB_CONSULTAS_MEDICAS")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConsultaMedica {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custom-sequence-generator")
    @GenericGenerator(
            name = "custom-sequence-generator",
            strategy = "org.roguesoft.docapp.infrastructure.model.sequence.CustomSequenceIdGenerator",
            parameters = {
                    @Parameter(name = CustomSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "CSM")
            }
    )
    @Column(name = "cod_consulta", length = 20, nullable = false)
    private String id;

    @Column(name = "data", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "horario", nullable = false)
    @Temporal(TemporalType.TIME)
    private Time horario;

    private BigDecimal valor;

    private String status;

    @ManyToOne
    @JoinColumn(name = "cod_individuo", referencedColumnName = "cod_individuo")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "cod_medico", referencedColumnName = "cod_medico")
    private Medico medico;

}
