package org.roguesoft.docapp.infrastructure.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "TB_CONVENIOS")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Convenio {

    @Id
    @Column(name = "cod_individuo", length = 20, nullable = false)
    private String id;

    @Column(name = "cnpj", length = 14, unique = true, nullable = false)
    private String cnpj;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "cod_individuo")
    private Individuo individuo;

}
