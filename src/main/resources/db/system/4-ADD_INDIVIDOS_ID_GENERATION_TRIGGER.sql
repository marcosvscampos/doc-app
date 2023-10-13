DELIMITER $$

CREATE TRIGGER tr_generate_custom_id_individuos
BEFORE INSERT ON TB_INDIVIDUOS
FOR EACH ROW
BEGIN
    SET NEW.cod_individuo = generate_custom_id();
END;
$$

DELIMITER ;