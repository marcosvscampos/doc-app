package org.roguesoft.docapp.infrastructure.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "TB_INDIVIDUOS")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Individuo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custom-sequence-generator")
    @GenericGenerator(
            name = "custom-sequence-generator",
            strategy = "org.roguesoft.docapp.infrastructure.model.sequence.CustomSequenceIdGenerator",
            parameters = {
                    @Parameter(name = CustomSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "IND")
            }
    )
    @Column(name = "cod_individuo", length = 20, nullable = false)
    private String id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "telefone", length = 15, nullable = false)
    private String telefone;

    @Column(name = "rua", length = 50, nullable = false)
    private String rua;

    @Column(name = "bairro", length = 25, nullable = false)
    private String bairro;

    @Column(name = "cidade", length = 30, nullable = false)
    private String cidade;

    @Column(name = "numero", length = 5, nullable = false)
    private String numero;

    @Column(name = "cep", length = 10, nullable = false)
    private String cep;

    @Column(name = "estado", length = 2, nullable = false)
    private String estado;

}
