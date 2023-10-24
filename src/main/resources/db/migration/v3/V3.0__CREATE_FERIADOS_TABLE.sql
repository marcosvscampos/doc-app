CREATE TABLE TB_FERIADOS (
                id_feriado INTEGER AUTO_INCREMENT NOT NULL,
                dia NUMERIC NOT NULL,
                mes NUMERIC NOT NULL,
                descricao VARCHAR(255) NOT NULL,
                PRIMARY KEY (id_feriado)
);
