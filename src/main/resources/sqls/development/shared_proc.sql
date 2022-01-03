use
    CSCI5308_7_DEVINT;

DROP PROCEDURE IF EXISTS SP_CREATE_SHARED_TABLES;

delimiter |
CREATE PROCEDURE SP_CREATE_SHARED_TABLES()
BEGIN

    DROP TABLE IF EXISTS `CSCI5308_7_DEVINT`.`user_credential`;
    CREATE TABLE IF NOT EXISTS `CSCI5308_7_DEVINT`.`user_credential`
    (
        `user_id`               VARCHAR(255)                  NOT NULL,
        `password`              VARCHAR(45)                   NOT NULL,
        `is_hard_blocked`       ENUM ('yes','no')             NOT NULL DEFAULT 'no',
        `is_soft_blocked`       ENUM ('yes','no')             NOT NULL DEFAULT 'no',
        `last_login_time`       DATETIME                      NOT NULL DEFAULT NOW(),
        `security_id`           VARCHAR(500)                  NOT NULL,
        `security_answer_one`   VARCHAR(500)                  NOT NULL,
        `security_answer_two`   VARCHAR(500)                  NOT NULL,
        `security_answer_three` VARCHAR(500)                  NOT NULL,
        `failed_login_count`    INT                           NOT NULL DEFAULT 0,
        `role_type`             ENUM ('student', 'institute', 'ministry') NOT NULL,
        `is_blackListed`        ENUM ('yes', 'no')            NOT NULL DEFAULT 'no',
        PRIMARY KEY (`user_id`)
    ) ENGINE = InnoDB
      DEFAULT CHARACTER SET = utf8mb3;

    DROP TABLE IF EXISTS `CSCI5308_7_DEVINT`.`feedback`;

    CREATE TABLE IF NOT EXISTS `CSCI5308_7_DEVINT`.`feedback`
    (
       institute_id int    default 0 null,
       answer_one   double default 0 not null,
       answer_two   double default 0 not null,
       answer_three double default 0 not null,
       answer_four  double default 0 not null,
       answer_five  double default 0 not null,
       total_rating double default 0 not null
    ) ENGINE = InnoDB
      DEFAULT CHARACTER SET = utf8mb3;
END
|

CALL SP_CREATE_SHARED_TABLES();

SHOW
    PROCEDURE STATUS;