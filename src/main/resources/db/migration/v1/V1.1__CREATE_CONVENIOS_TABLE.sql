CREATE TABLE TB_CONVENIOS (
                cod_individuo VARCHAR(20) NOT NULL,
                cnpj VARCHAR(14) UNIQUE NOT NULL,
                PRIMARY KEY (cod_individuo)
);