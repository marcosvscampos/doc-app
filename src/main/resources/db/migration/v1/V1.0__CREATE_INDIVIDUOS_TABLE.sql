CREATE TABLE TB_INDIVIDUOS (
                cod_individuo VARCHAR(20) NOT NULL,
                nome VARCHAR(255) NOT NULL,
                telefone VARCHAR(15) NOT NULL,
                rua VARCHAR(50) NOT NULL,
                bairro VARCHAR(50) NOT NULL,
                cidade VARCHAR(30) NOT NULL,
                numero VARCHAR(5) DEFAULT 'S/N',
                cep VARCHAR(10) NOT NULL,
                estado VARCHAR(2) NOT NULL,
                PRIMARY KEY (cod_individuo)
);
