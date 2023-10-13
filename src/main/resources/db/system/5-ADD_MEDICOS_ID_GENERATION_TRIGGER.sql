DELIMITER $$

CREATE TRIGGER tr_generate_custom_id_medicos
BEFORE INSERT ON TB_MEDICOS
FOR EACH ROW
BEGIN
    SET NEW.cod_medico = generate_custom_id();
END;
$$

DELIMITER ;