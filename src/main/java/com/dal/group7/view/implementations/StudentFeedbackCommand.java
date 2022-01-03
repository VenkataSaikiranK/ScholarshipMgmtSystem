package com.dal.group7.view.implementations;

import com.dal.group7.service.implementation.StudentFeedbackService;
import com.dal.group7.view.interfaces.Command;

import static com.dal.group7.constants.ViewConstants.PROGRAM_MESSAGE_POSTFIX;
import static com.dal.group7.constants.ViewConstants.PROGRAM_MESSAGE_PREFIX;
import static com.dal.group7.view.implementations.CommandFactory.ERROR;
import static com.dal.group7.view.implementations.CommandFactory.STUDENT_HOME;

public class StudentFeedbackCommand extends Command {
    private StudentFeedbackService studentFeedbackService;
    private boolean success;

    public StudentFeedbackCommand(StudentFeedbackService studentService) {
        this.studentFeedbackService = studentService;
    }

    @Override
    public void printView() {
        System.out.println("Fill the questionnaire which will be available at : /var/tmp/feedback.json");
    }

    @Override
    public void handle() {
        String input = scanner.nextLine();
        try {
            this.success = true;
            studentFeedbackService.saveFeedback(input);
            System.out.println(PROGRAM_MESSAGE_PREFIX + " Thanks for the feedback " + PROGRAM_MESSAGE_POSTFIX);
        }catch (Exception exception){
            System.out.println(PROGRAM_MESSAGE_PREFIX + exception.getMessage() + PROGRAM_MESSAGE_POSTFIX);
            this.success = false;
        }
    }

    @Override
    public void setNextCommand() {
        nextCommand = success ? STUDENT_HOME.getCommand() : ERROR.getCommand();
    }
}
