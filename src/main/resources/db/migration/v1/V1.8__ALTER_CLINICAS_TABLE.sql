ALTER TABLE TB_CLINICAS ADD CONSTRAINT tb_convenios_tb_clinicas_fk
FOREIGN KEY (cod_convenio_gerenciador)
REFERENCES TB_CONVENIOS (cod_individuo)
ON DELETE CASCADE
ON UPDATE NO ACTION;