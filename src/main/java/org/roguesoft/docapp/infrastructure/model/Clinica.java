package org.roguesoft.docapp.infrastructure.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
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
@Table(name = "TB_CLINICAS")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Clinica {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custom-sequence-generator")
    @GenericGenerator(
            name = "custom-sequence-generator",
            strategy = "org.roguesoft.docapp.infrastructure.model.sequence.CustomSequenceIdGenerator",
            parameters = {
                    @Parameter(name = CustomSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "CLN")
            }
    )
    @Column(name = "cod_clinica", length = 20, nullable = false)
    private String id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "telefone", length = 15, nullable = false)
    private String telefone;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "cod_convenio_gerenciador", referencedColumnName = "cod_individuo")
    private Convenio convenio;

}
