CREATE TABLE TB_CLINICAS (
                cod_clinica VARCHAR(20) NOT NULL,
                nome VARCHAR(255) NOT NULL,
                telefone VARCHAR(15) NOT NULL,
                email VARCHAR(100) NOT NULL,
                cod_convenio_gerenciador VARCHAR(20) NOT NULL,
                PRIMARY KEY (cod_clinica)
);