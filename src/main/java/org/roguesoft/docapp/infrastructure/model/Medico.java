package org.roguesoft.docapp.infrastructure.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.roguesoft.docapp.infrastructure.model.sequence.CustomSequenceIdGenerator;

@Data
@Entity
@Table(name = "TB_MEDICOS")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custom-sequence-generator")
    @GenericGenerator(
            name = "custom-sequence-generator",
            strategy = "org.roguesoft.docapp.infrastructure.model.sequence.CustomSequenceIdGenerator",
            parameters = {
                    @Parameter(name = CustomSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "MED")
            }
    )
    @Column(name = "cod_medico", length = 20, nullable = false)
    private String id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "especialidade", length = 30, nullable = false)
    private String especialidade;

    @ManyToOne
    @JoinColumn(name = "cod_clinica", referencedColumnName = "cod_clinica")
    private Clinica clinica;

}
