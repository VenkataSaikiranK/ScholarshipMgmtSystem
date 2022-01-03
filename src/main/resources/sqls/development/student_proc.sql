DROP PROCEDURE IF EXISTS SP_CREATE_STUDENT_TABLES;

delimiter |
CREATE PROCEDURE SP_CREATE_STUDENT_TABLES()
BEGIN

    DROP TABLE IF EXISTS `CSCI5308_7_DEVINT`.`student_basic`;
    -- Student Basic
    CREATE TABLE IF NOT EXISTS `CSCI5308_7_DEVINT`.`student_basic`
    (
        `first_name`      VARCHAR(255)                          NOT NULL,
        `last_name`       VARCHAR(255)                          NOT NULL,
        `email_id`        VARCHAR(255)                          NOT NULL,
        `phone_number`    VARCHAR(45)                           NOT NULL,
        `passport_number` VARCHAR(45)                           NOT NULL,
        `dob`             DATETIME                              NOT NULL,
        `gender`          ENUM ('female', 'male', 'non binary') NOT NULL,
        `state`           VARCHAR(45)                           NOT NULL,
        `city`            VARCHAR(45)                           NOT NULL,
        `pincode`         VARCHAR(45)                           NOT NULL,
        `country`         VARCHAR(45)                           NOT NULL,
        PRIMARY KEY (`email_id`)
    ) ENGINE = INNODB
      DEFAULT CHARACTER SET = UTF8MB3;

    -- Student Academic
    DROP TABLE IF EXISTS `CSCI5308_7_DEVINT`.`student_academic`;

    CREATE TABLE IF NOT EXISTS `CSCI5308_7_DEVINT`.`student_academic`
    (
        `email_id`                   VARCHAR(255) NOT NULL,
        `institute_id`               INT          NULL DEFAULT NULL,
        `GPA-X`                      DOUBLE       NULL DEFAULT NULL,
        `GPA-XII`                    DOUBLE       NULL DEFAULT NULL,
        `GPA_Bachelors`              DOUBLE       NULL DEFAULT NULL,
        `BOARD-X`                    VARCHAR(45)  NULL DEFAULT NULL,
        `BOARD-XII`                  VARCHAR(45)  NULL DEFAULT NULL,
        `backlog_count_X`            INT          NULL DEFAULT NULL,
        `backlog_count_XII`          INT          NULL DEFAULT NULL,
        `backlog_count_bachelors`    INT          NULL DEFAULT NULL,
        `joining_month_bachelors`    DATETIME     NULL DEFAULT NULL,
        `graduation_month_bachelors` DATETIME     NULL DEFAULT NULL,
        PRIMARY KEY (`email_id`)
    ) ENGINE = INNODB
      DEFAULT CHARACTER SET = UTF8MB3;

    -- student Non Academic
    DROP TABLE IF EXISTS `CSCI5308_7_DEVINT`.`student_non_academic`;

    CREATE TABLE IF NOT EXISTS `CSCI5308_7_DEVINT`.`student_non_academic`
    (
        `email_id`                     VARCHAR(255) NOT NULL,
        `national_sports_awards_count` INT          NULL DEFAULT '0',
        `state_sports_awards_count`    INT          NULL DEFAULT '0',
        `district_sports_awards_count` INT          NULL DEFAULT '0',
        `national_arts_awards_count`   INT          NULL DEFAULT '0',
        `state_arts_awards_count`      INT          NULL DEFAULT '0',
        `district_arts_awards_count`   INT          NULL DEFAULT '0',
        PRIMARY KEY (`email_id`)
    ) ENGINE = INNODB
      DEFAULT CHARACTER SET = UTF8MB3;

    -- student finance
    DROP TABLE IF EXISTS `CSCI5308_7_DEVINT`.`student_finance`;

    CREATE TABLE IF NOT EXISTS `CSCI5308_7_DEVINT`.`student_finance`
    (
        `email_id`             VARCHAR(255) NOT NULL,
        `bank_acc_num`         VARCHAR(255) NULL DEFAULT NULL,
        `bank_IFSC`            VARCHAR(255) NULL DEFAULT NULL,
        `annual_income`        DOUBLE       NULL DEFAULT NULL,
        `bank_name`            VARCHAR(255) NULL DEFAULT NULL,
        `bank_acc_holder_name` VARCHAR(255) NULL DEFAULT NULL,
        PRIMARY KEY (`email_id`)
    ) ENGINE = INNODB
      DEFAULT CHARACTER SET = UTF8MB3;

    DROP TABLE IF EXISTS feedback;
    create table feedback
    (
        institute_id int    default 0 null,
        answer_one   double default 0 not null,
        answer_two   double default 0 not null,
        answer_three double default 0 not null,
        answer_four  double default 0 not null,
        answer_five  double default 0 not null,
        total_rating double default 0 not null
    ) ENGINE = INNODB
      DEFAULT CHARACTER SET = UTF8MB3;

END
|

CALL SP_CREATE_STUDENT_TABLES();