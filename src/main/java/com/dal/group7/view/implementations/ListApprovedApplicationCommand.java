package com.dal.group7.view.implementations;

import com.dal.group7.persistent.model.Application;
import com.dal.group7.service.implementation.InstituteApplicationService;
import com.dal.group7.view.interfaces.Command;

import java.util.List;

import static com.dal.group7.constants.ViewConstants.*;
import static com.dal.group7.view.implementations.CommandFactory.ERROR;
import static com.dal.group7.view.implementations.CommandFactory.INSTITUTE_HOME;

public class ListApprovedApplicationCommand extends Command {

    private boolean result;
    private InstituteApplicationService instituteApplicationService;
    private int input;

    public ListApprovedApplicationCommand(InstituteApplicationService instituteApplicationService){
        this.instituteApplicationService = instituteApplicationService;
    }

    @Override
    public void printView() {
        System.out.println(VIEW_APPROVEDAPPLICATION);
        System.out.print(ENTER_ID);
        this.input = scanner.nextInt();
    }

    @Override
    public void handle() {
        try {
            printOnConsole(instituteApplicationService
                    .displayApprovedApplicationsByInstitute(input));
            this.result = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            this.result = false;
        }
    }

    private void printOnConsole(List<Application> applications) {
        for (Application scheme : applications) {
            System.out.print(System.lineSeparator());
            System.out.println(STARS + scheme.getApplicationId() + STARS);
            System.out.println("Applied Date: " + scheme.getAppliedDate());
            System.out.println("Student ID: " + scheme.getStudentId());
            System.out
                    .println(
                            "Institute Status: " + scheme.getInstituteStatus());
        }
        System.out.print(System.lineSeparator());
    }

    @Override
    public void setNextCommand() {
        this.nextCommand = this.result ? INSTITUTE_HOME.getCommand() : ERROR.getCommand();
    }
}
