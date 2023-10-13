CREATE TABLE TB_PACIENTES (
                cod_individuo VARCHAR(20) NOT NULL,
                cpf VARCHAR(11) UNIQUE NOT NULL,
                rg VARCHAR(15) UNIQUE NOT NULL,
                PRIMARY KEY (cod_individuo)
);