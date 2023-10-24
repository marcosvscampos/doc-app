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

@Data
@Entity
@Table(name = "TB_FERIADOS")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Feriado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_feriado", nullable = false, unique = true)
    private Integer id;

    @Column(name = "dia", nullable = false)
    private Integer dia;

    @Column(name = "mes", nullable = false)
    private Integer mes;

    @Column(name = "descricao", nullable = false)
    private String description;


}
