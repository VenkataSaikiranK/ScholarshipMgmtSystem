package com.dal.group7.view.implementations;

import com.dal.group7.service.implementation.StudentService;
import com.dal.group7.view.interfaces.Command;

import static com.dal.group7.constants.ViewConstants.*;
import static com.dal.group7.view.implementations.CommandFactory.ERROR;
import static com.dal.group7.view.implementations.CommandFactory.STUDENT_HOME;

public class StudentSignupCommand extends Command {
    private String input;
    private Boolean result;
    public StudentService studentService;


    StudentSignupCommand(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void printView() {
        System.out.println(ENTER_STUDENT_FILE);
        System.out.print(PROMPT_PREFIX + FILLED_FILE);
    }

    @Override
    public void handle() {
        this.input = scanner.nextLine();
        try {
            System.out.println(INSERTING_STUDENT);
            studentService.signup(input);
            System.out.println(PROGRAM_MESSAGE_PREFIX + SIGNED_UP_AS_STUDENT +
                    PROGRAM_MESSAGE_POSTFIX);
            this.result = true;
        } catch (Exception exception) {
            this.result = false;
            System.out.println(
                    PROGRAM_MESSAGE_PREFIX + exception.getLocalizedMessage() +
                            PROGRAM_MESSAGE_POSTFIX);
        }
    }

    @Override
    public void setNextCommand() {
        this.nextCommand =
                result ? STUDENT_HOME.getCommand() : ERROR.getCommand();
    }
}
