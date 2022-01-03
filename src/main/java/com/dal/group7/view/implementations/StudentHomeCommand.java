package com.dal.group7.view.implementations;

import com.dal.group7.view.interfaces.Command;

import static com.dal.group7.constants.ViewConstants.*;
import static com.dal.group7.view.implementations.CommandFactory.*;

public class StudentHomeCommand extends Command {
    private Integer input;

    @Override
    public void printView() {
        System.out.println(System.lineSeparator() + STUDENT_MENU);
        System.out.println(SCHOLARSHIP_SCHEMES);
        System.out.println(APPLY_FOR_A_SCHEME);
        System.out.println(CHECK_THE_STATUS_OF_THE_APPLIED_SCHEME);
        System.out.println(GIVE_FEEDBACK);
        System.out.println("5. Logout" + System.lineSeparator());
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
                this.nextCommand = LIST_SCHOLARSHIPS.getCommand();
                break;
            case 2:
                this.nextCommand = APPLY_FOR_SCHEME.getCommand();
                break;
            case 3:
                this.nextCommand = VIEW_APPLICATION_STATUS.getCommand();
                break;
            case 4:
                this.nextCommand = SAVE_FEEDBACK.getCommand();
                break;
            default:
                this.nextCommand = QUIT.getCommand();
        }
    }
}
