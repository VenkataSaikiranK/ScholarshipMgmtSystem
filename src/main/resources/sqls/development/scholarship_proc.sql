DROP procedure IF EXISTS `SP_CREATE_SCHOLARSHIP_TABLES`;

DELIMITER |
CREATE PROCEDURE SP_CREATE_SCHOLARSHIP_TABLES()
BEGIN
    DROP TABLE IF EXISTS `CSCI5308_7_DEVINT`.`scholarship`;
    CREATE TABLE IF NOT EXISTS `CSCI5308_7_DEVINT`.`scholarship`
    (
        `scholarship_id`     INT         NOT NULL AUTO_INCREMENT,
        `name`               VARCHAR(45) NOT NULL,
        `effectiveDate`      date        NOT NULL,
        `tuition_amount` double      NOT NULL,
        `living_expenses_amount` double      NOT NULL,
        `travel_amount` double      NOT NULL,
        `insurance_amount` double      NOT NULL,
        `criteria_girl`      boolean     NOT NULL,
        `criteria_academic`  boolean     NOT NULL,
        `criteria_sports`    boolean     NOT NULL,
        PRIMARY KEY (`scholarship_id`)
    )
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = UTF8MB3;
    DROP TABLE IF EXISTS `CSCI5308_7_DEVINT`.`application`;

    CREATE TABLE IF NOT EXISTS `CSCI5308_7_DEVINT`.`application`
    (
        `application_id`         INT                                                                   NOT NULL,
        `scheme_id`              INT                                                                   NULL     DEFAULT NULL,
        `student_id`             VARCHAR(255)                                                          NULL     DEFAULT NULL,
        `institute_id`           INT                                                                   NULL     DEFAULT NULL,
        `applied_date`           DATETIME                                                              NOT NULL DEFAULT NOW(),
        `last_update`            DATETIME                                                              NOT NULL DEFAULT NOW(),
        `application_status`     ENUM ('SUBMITTED', 'APPROVED', 'REJECTED', 'CLOSED')                  NOT NULL,
        `institute_status`       ENUM ('UNRECEIVED','PICKED', 'APPROVED', 'REJECTED', 'FUND_RECEIVED') NOT NULL DEFAULT 'UNRECEIVED',
        `ministry_status`        ENUM ('UNRECEIVED','PICKED', 'APPROVED', 'REJECTED', 'FUND_ISSUED')   NOT NULL DEFAULT 'UNRECEIVED',
        `academic_score`         DOUBLE                                                                NOT NULL DEFAULT 0,
        `non_academic_score`     DOUBLE                                                                NOT NULL DEFAULT 0,
        `profile_score`          DOUBLE                                                                NOT NULL DEFAULT 0,
        `tuition_amount`         DOUBLE                                                                NOT NULL DEFAULT 0,
        `insurance_amount`       DOUBLE                                                                NOT NULL DEFAULT 0,
        `travel_amount`          DOUBLE                                                                NOT NULL DEFAULT 0,
        `living_expenses_amount` DOUBLE                                                                NOT NULL DEFAULT 0,
        PRIMARY KEY (`application_id`)
    )
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3;
    alter table application
        alter column tuition_amount set default 0;
    alter table application
        alter column insurance_amount set default 0;
    alter table application
        alter column travel_amount set default 0;
    alter table application
        alter column living_expenses_amount set default 0;


    alter table application
        modify `application_status` enum ('SUBMITTED','APPROVED','REJECTED','CLOSED', 'HOLD');
END
|

CALL SP_CREATE_SCHOLARSHIP_TABLES();