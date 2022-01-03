package com.dal.group7.view.implementations;

import com.dal.group7.service.implementation.MinistryLoginService;
import com.dal.group7.view.interfaces.Command;

import static com.dal.group7.constants.ViewConstants.*;
import static com.dal.group7.view.implementations.CommandFactory.ERROR;
import static com.dal.group7.view.implementations.CommandFactory.MINISTRY_HOME;

public class MinistryLoginCommand extends Command {
    private String userName;
    private String password;
    private final MinistryLoginService ministryLoginService;
    private boolean success;

    public MinistryLoginCommand(MinistryLoginService loginService) {
        this.ministryLoginService = loginService;
    }

    @Override
    public void printView() {
        System.out.print(PROMPT_PREFIX + ENTER_USERNAME);
        this.userName = scanner.nextLine();
        System.out.print(PROMPT_PREFIX + PASSWORD);
        this.password = scanner.nextLine();
    }

    @Override
    public void handle() {
        try {
            ministryLoginService.userLogin(userName, password);
            this.success = true;
        } catch (Exception exception) {
            System.out.println(PROGRAM_MESSAGE_PREFIX + exception.getMessage() + PROGRAM_MESSAGE_POSTFIX);
            this.success = false;
        }
    }

    @Override
    public void setNextCommand() {
        nextCommand = success ? MINISTRY_HOME.getCommand() : ERROR.getCommand();
    }
}
