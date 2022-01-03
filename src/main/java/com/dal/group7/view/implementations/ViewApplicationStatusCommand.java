package com.dal.group7.view.implementations;

import com.dal.group7.persistent.model.Application;
import com.dal.group7.service.implementation.StudentSchemeService;
import com.dal.group7.view.interfaces.Command;

import java.util.List;

import static com.dal.group7.constants.ViewConstants.*;
import static com.dal.group7.view.implementations.CommandFactory.ERROR;
import static com.dal.group7.view.implementations.CommandFactory.STUDENT_HOME;

public class ViewApplicationStatusCommand extends Command {

    private StudentSchemeService studentSchemeService;
    private String userId;
    private Boolean result;

    public ViewApplicationStatusCommand(
            StudentSchemeService applySchemeService) {
        this.studentSchemeService = applySchemeService;
    }

    @Override
    public void printView() {
        System.out.println(CHECKING_STATUS);
        System.out.println(ENTER_SECURITY_DETAILS);
        System.out.print(PROMPT_PREFIX + USER_ID);
        this.userId = scanner.nextLine();
    }

    @Override
    public void handle() {
        try {
            this.result = true;
            printOnConsole(studentSchemeService.viewStatus(userId));
        } catch (Exception exception) {
            this.result = false;
            System.out.println(PROGRAM_MESSAGE_PREFIX + exception.getMessage() +
                    PROGRAM_MESSAGE_POSTFIX);
        }
    }

    private void printOnConsole(List<Application> applications) {
        if (applications.isEmpty()) {
            System.out.println(PROGRAM_MESSAGE_PREFIX + NO_APPLICATIONS_FOUND +
                    PROGRAM_MESSAGE_POSTFIX);
        } else {
            System.out.println(System.lineSeparator());
            for (Application application : applications) {

                System.out.println(STARS +
                        application.getApplicationId() + STARS);
                System.out.println(APPLIED_ON + application.getAppliedDate());
                System.out.println(LAST_UPDATE + application.getLastUpdate());
                System.out.println(APPLICATION_STATUS +
                        application.getApplicationStatus());
                System.out.println(
                        INSTITUTE_STATUS + application.getInstituteStatus());
                System.out.println(
                        MINISTRY_STATUS + application.getMinistryStatus());
                System.out.println(System.lineSeparator());
            }
        }
    }

    @Override
    public void setNextCommand() {
        this.nextCommand =
                result ? STUDENT_HOME.getCommand() : ERROR.getCommand();
    }
}
