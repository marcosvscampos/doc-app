CREATE TABLE TB_CONSULTAS_MEDICAS (
                cod_consulta VARCHAR(20) NOT NULL,
                data DATE NOT NULL,
                horario TIME NOT NULL,
                valor DECIMAL NOT NULL,
                status VARCHAR(20) DEFAULT 'MARCADO' NOT NULL,
                cod_individuo VARCHAR(20) NOT NULL,
                cod_medico VARCHAR(20) NOT NULL,
                PRIMARY KEY (cod_consulta)
);