package com.dal.group7.view.implementations;

import com.dal.group7.view.interfaces.Command;

import static com.dal.group7.constants.ViewConstants.*;
import static com.dal.group7.view.implementations.CommandFactory.QUIT;

public class SelectLoginUserCommand extends Command {
    private Integer input;

    @Override
    public void printView() {
        System.out.println(LOGIN_AS);
        System.out.println(STUDENT);
        System.out.println(INSTITUTE);
        System.out.println(MINISTRY);
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
                nextCommand = CommandFactory.STUDENT_LOGIN.getCommand();
                System.setProperty(UserType.USER.toString(),
                        UserType.STUDENT.toString());
                break;
            case 2:
                nextCommand = CommandFactory.INSTITUTE_LOGIN.getCommand();
                System.setProperty(UserType.USER.toString(),
                        UserType.INSTITUTE.toString());
                break;
            case 3:
                nextCommand = CommandFactory.MINISTRY_LOGIN.getCommand();
                System.setProperty(UserType.USER.toString(),
                        UserType.MINISTRY.toString());
                break;
            default:
                nextCommand = QUIT.getCommand();
        }
    }
}
