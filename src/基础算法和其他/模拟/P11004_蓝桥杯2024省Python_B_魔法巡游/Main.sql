DELIMITER //

CREATE PROCEDURE MagicTour()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE n, i, temp, inp, count INT;
    DECLARE cur CURSOR FOR SELECT s, t FROM magic_table ORDER BY id;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    DROP TEMPORARY TABLE IF EXISTS temp_table;
    CREATE TEMPORARY TABLE temp_table (
                                          id INT AUTO_INCREMENT PRIMARY KEY,
                                          s INT,
                                          t INT
    );

    -- 假设数据已预先插入temp_table
    SET inp = 1, count = 0;

    OPEN cur;
    read_loop: LOOP
        FETCH cur INTO @s_val, @t_val;
        IF done THEN
            LEAVE read_loop;
        END IF;

        SET temp = IF(inp % 2 = 1, @s_val, @t_val);

        -- 数字检查逻辑
        IF temp = 0 THEN
            SET count = count + 1;
            SET inp = inp + 1;
        ELSE
            SET @n = temp;
            check_loop: WHILE @n > 0 DO
                    SET @digit = @n % 10;
                    IF @digit IN (0, 2, 4) THEN
                        SET count = count + 1;
                        SET inp = inp + 1;
                        LEAVE check_loop;
                    END IF;
                    SET @n = FLOOR(@n / 10);
                END WHILE check_loop;
        END IF;
    END LOOP;

    CLOSE cur;
    SELECT count;
END //

DELIMITER ;