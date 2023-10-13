DELIMITER $$
CREATE FUNCTION generate_custom_id()
RETURNS CHAR(12)
BEGIN
    DECLARE custom_id CHAR(12);
    SET custom_id = CONCAT(
        CHAR(65 + FLOOR(RAND() * 26), CHAR(65 + FLOOR(RAND() * 26), CHAR(65 + FLOOR(RAND() * 26))),
        LPAD(FLOOR(RAND() * 1000000), 6, '0'),
        CHAR(65 + FLOOR(RAND() * 26), FLOOR(RAND() * 10)
    );
    RETURN custom_id;
END;
$$
DELIMITER ;