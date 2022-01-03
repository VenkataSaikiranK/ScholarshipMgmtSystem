package com.dal.group7.view.implementations;

import com.dal.group7.service.implementation.MinistryApplicationService;
import com.dal.group7.view.interfaces.Command;

import static com.dal.group7.constants.ViewConstants.PROGRAM_MESSAGE_POSTFIX;
import static com.dal.group7.constants.ViewConstants.PROGRAM_MESSAGE_PREFIX;
import static com.dal.group7.view.implementations.CommandFactory.ERROR;
import static com.dal.group7.view.implementations.CommandFactory.MINISTRY_HOME;

public class AwardInstitutesCommand extends Command {
    private final MinistryApplicationService ministryApplicationService;
    private boolean success;

    public AwardInstitutesCommand(
            MinistryApplicationService ministryApplicationService) {
        this.ministryApplicationService = ministryApplicationService;
    }

    @Override
    public void printView() {
        System.out.println("Awarding the Institutes");
    }

    @Override
    public void handle() {
        try {
            ministryApplicationService.awardInstitutes();
            this.success = true;
            System.out.println(
                    PROGRAM_MESSAGE_PREFIX + "Awarded Institutes...." +
                            PROGRAM_MESSAGE_POSTFIX);
        } catch (Exception exception) {
            System.out.println(PROGRAM_MESSAGE_PREFIX + exception.getMessage() +
                    PROGRAM_MESSAGE_POSTFIX);
            this.success = false;
        }
    }

    @Override
    public void setNextCommand() {
        this.nextCommand =
                success ? MINISTRY_HOME.getCommand() : ERROR.getCommand();
    }
}
