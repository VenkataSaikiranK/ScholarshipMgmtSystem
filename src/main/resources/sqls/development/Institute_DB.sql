use CSCI5308_7_DEVINT;
delimiter |
DROP PROCEDURE SP_CREATE_INSTITUTE_TABLES;
CREATE PROCEDURE SP_CREATE_INSTITUTE_TABLES()
BEGIN
    DROP TABLE IF EXISTS `CSCI5308_7_DEVINT`.`institute_basic`;
    CREATE TABLE IF NOT EXISTS `CSCI5308_7_DEVINT`.`institute_basic`
    (
        `institute_id`      INT          NOT NULL,
        `name`              VARCHAR(45)  NOT NULL,
        `email`             VARCHAR(255) NOT NULL,
        `regd_code`         VARCHAR(45)  NOT NULL,
        `address`           VARCHAR(45)  NOT NULL,
        `city`              VARCHAR(45)  NOT NULL,
        `institute_state`   VARCHAR(45)  NOT NULL,
        `institute_contact` VARCHAR(45)  NOT NULL,
        `country`           VARCHAR(45)  NOT NULL,
        `pincode`           VARCHAR(45)  NOT NULL,
        PRIMARY KEY (`email`)
    )
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = UTF8MB3;

    alter table institute_basic
        add grant_amount double default 0 null;

END 
|
CALL SP_CREATE_INSTITUTE_TABLES();