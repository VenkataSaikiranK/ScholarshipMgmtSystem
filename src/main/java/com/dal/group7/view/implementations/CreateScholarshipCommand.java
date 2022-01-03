package com.dal.group7.view.implementations;

import com.dal.group7.service.implementation.MinistryScholarshipService;
import com.dal.group7.view.interfaces.Command;

import java.io.IOException;
import java.sql.SQLException;

import static com.dal.group7.constants.ViewConstants.*;
import static com.dal.group7.view.implementations.CommandFactory.ERROR;
import static com.dal.group7.view.implementations.CommandFactory.MINISTRY_HOME;

public class CreateScholarshipCommand extends Command {
    private final MinistryScholarshipService ministryScholarshipService;
    private boolean successFul;

    public CreateScholarshipCommand(MinistryScholarshipService ministryScholarshipService) {
        this.ministryScholarshipService = ministryScholarshipService;
    }

    @Override
    public void printView() {
        System.out.println(ENTER_SCHOLARSHIP_FILE_PATH);
        System.out.print(PROMPT_PREFIX + FILLED_FILE);
    }

    @Override
    public void handle() {
        String input = scanner.nextLine();
        try {
            ministryScholarshipService.saveScholarship(input);
            System.out.println(PROGRAM_MESSAGE_PREFIX + SUCCESSFULLY_CREATED_SCHOLARSHIP + PROGRAM_MESSAGE_POSTFIX);
            this.successFul = true;
        } catch (SQLException | IOException exception) {
           this.successFul = false;
        }
    }

    @Override
    public void setNextCommand() {
        this.nextCommand = this.successFul ? MINISTRY_HOME.getCommand() : ERROR.getCommand();
    }
}
