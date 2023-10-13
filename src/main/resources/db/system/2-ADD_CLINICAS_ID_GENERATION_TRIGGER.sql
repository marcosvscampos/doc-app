DELIMITER $$

CREATE TRIGGER tr_generate_custom_id_clinicas
BEFORE INSERT ON TB_CLINICAS
FOR EACH ROW
BEGIN
    SET NEW.cod_clinica = generate_custom_id();
END;
$$

DELIMITER ;