package com.dal.group7.view.implementations;

import com.dal.group7.view.interfaces.Command;

import static com.dal.group7.constants.ViewConstants.*;
import static com.dal.group7.view.implementations.CommandFactory.*;


public class SignUpCommand extends Command {
    private Integer input;

    @Override
    public void printView() {
        System.out.println(SIGN_UP_AS);
        System.out.println(STUDENT);
        System.out.println(INSTITUTE);
        System.out.print(PROMPT_PREFIX + PLEASE_SELECT_YOUR_OPTION);
    }

    @Override
    public void handle() {
        this.input = scanner.nextInt();
    }

    @Override
    public void setNextCommand() {
        switch (input) {
            case 1:
                nextCommand = STUDENT_SIGNUP.getCommand();
                System.setProperty(UserType.USER.toString(),
                        UserType.STUDENT.toString());
                break;
            case 2:
                nextCommand = INSTITUTE_SIGNUP.getCommand();
                System.setProperty(UserType.USER.toString(),
                        UserType.INSTITUTE.toString());
                break;
            default:
                nextCommand = QUIT.getCommand();
        }
    }
}
