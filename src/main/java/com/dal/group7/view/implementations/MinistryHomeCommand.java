package com.dal.group7.view.implementations;

import com.dal.group7.view.interfaces.Command;

import static com.dal.group7.constants.ViewConstants.*;
import static com.dal.group7.view.implementations.CommandFactory.*;

public class MinistryHomeCommand extends Command {
    private Integer input;


    @Override
    public void printView() {
        System.out.println(HEADER + MINISTRY_PORTAL + HEADER);
        System.out.println(VIEW_APPROVED_STUDENT_APPLICATIONS);
        System.out.println(CREATE_SCHOLARSHIP1);
        System.out.println(APPROVE_REJECT_APPLICATION);
        System.out.println("4. Award Top Institutes");
        System.out.println("5. Logout");
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
                nextCommand = LIST_APPLICATION_MINISTRY.getCommand();
                break;
            case 2:
                nextCommand = CREATE_SCHOLARSHIP.getCommand();
                break;
            case 3:
                nextCommand = MINISTRY_DECISION.getCommand();
                break;
            case 4:
                nextCommand = AWARD_INSTITUTE.getCommand();
                break;
            case 5:
            default:
                nextCommand = QUIT.getCommand();
        }
    }
}
