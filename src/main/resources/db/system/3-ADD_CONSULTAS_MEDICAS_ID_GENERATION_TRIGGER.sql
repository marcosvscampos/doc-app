DELIMITER $$

CREATE TRIGGER tr_generate_custom_id_consultas_medicas
BEFORE INSERT ON TB_CONSULTAS_MEDICAS
FOR EACH ROW
BEGIN
    SET NEW.cod_consulta = generate_custom_id();
END;
&&

DELIMITER ;