package com.dal.group7.view.implementations;

import com.dal.group7.service.implementation.StudentSchemeService;
import com.dal.group7.view.interfaces.Command;

import static com.dal.group7.constants.ViewConstants.*;
import static com.dal.group7.view.implementations.CommandFactory.ERROR;
import static com.dal.group7.view.implementations.CommandFactory.STUDENT_HOME;

public class ApplySchemeCommand extends Command {

    public Boolean result;
    public String input;
    public StudentSchemeService studentSchemeService;

    ApplySchemeCommand(StudentSchemeService studentSchemeService) {
        this.studentSchemeService = studentSchemeService;
    }

    @Override
    public void printView() {
        System.out.println(ENTER_APPLY_SCHEME_FILE);
        System.out.print(PROMPT_PREFIX + FILLED_FILE);
    }

    @Override
    public void handle() {
        this.input = scanner.nextLine();
        try {
            System.out.println(APPLYING_SCHOLARSHIP);
            this.result = true;
            studentSchemeService.applyScheme(input);
            System.out.println(PROGRAM_MESSAGE_PREFIX + APPLICATION_SUBMITTED +
                    PROGRAM_MESSAGE_POSTFIX);
        } catch (Exception e) {
            this.result = false;
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void setNextCommand() {
        this.nextCommand =
                this.result ? STUDENT_HOME.getCommand() : ERROR.getCommand();
    }
}
