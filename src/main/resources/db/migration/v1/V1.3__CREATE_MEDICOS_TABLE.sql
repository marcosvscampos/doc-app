CREATE TABLE TB_MEDICOS (
                cod_medico VARCHAR(20) NOT NULL,
                nome VARCHAR(255) NOT NULL,
                especialidade VARCHAR(30) NOT NULL,
                cod_clinica VARCHAR(20) NOT NULL,
                PRIMARY KEY (cod_medico)
);