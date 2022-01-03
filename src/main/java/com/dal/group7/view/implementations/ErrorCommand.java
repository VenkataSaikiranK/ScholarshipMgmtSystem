package com.dal.group7.view.implementations;

import com.dal.group7.view.interfaces.Command;

import static com.dal.group7.constants.ViewConstants.*;
import static com.dal.group7.view.implementations.CommandFactory.QUIT;

public class ErrorCommand extends Command {

    @Override
    public void printView() {
        System.out.println(PROGRAM_MESSAGE_PREFIX + ERROR_IN_EXECUTION + PROGRAM_MESSAGE_POSTFIX);

    }

    @Override
    public void handle() {
    }

    @Override
    public void setNextCommand() {
        nextCommand = QUIT.getCommand();
    }
}
