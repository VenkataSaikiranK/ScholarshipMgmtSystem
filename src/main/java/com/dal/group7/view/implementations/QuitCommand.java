package com.dal.group7.view.implementations;

import com.dal.group7.view.interfaces.Command;

import static com.dal.group7.constants.ViewConstants.PROGRAM_MESSAGE_POSTFIX;
import static com.dal.group7.constants.ViewConstants.PROGRAM_MESSAGE_PREFIX;

public class QuitCommand extends Command {

    private static final String END_OF_PROGRAM = "End of program";

    @Override
    public void execute() {
        printView();
        handle();
    }

    @Override
    public void printView() {
        System.out.println(PROGRAM_MESSAGE_PREFIX + END_OF_PROGRAM + PROGRAM_MESSAGE_POSTFIX);
    }

    @Override
    public void handle() {
    }

    @Override
    public void setNextCommand() {
    }
}
