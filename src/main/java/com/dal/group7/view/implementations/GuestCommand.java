package com.dal.group7.view.implementations;

import com.dal.group7.persistent.model.Scholarship;
import com.dal.group7.service.implementation.MinistryScholarshipService;
import com.dal.group7.view.interfaces.Command;

import java.util.List;

import static com.dal.group7.constants.ViewConstants.*;
import static com.dal.group7.view.implementations.CommandFactory.ERROR;
import static com.dal.group7.view.implementations.CommandFactory.HOME;

public class GuestCommand extends Command {

    private String answerOne;

    private String answerTwo;

    private String answerThree;

    private boolean result;

    private MinistryScholarshipService scholarshipService;

    GuestCommand(MinistryScholarshipService scholarshipService){
        this.scholarshipService = scholarshipService;
    }

    @Override
    public void printView() {
        System.out.println(GUEST_QUESTION_ONE);
        this.answerOne = scanner.nextLine();
        System.out.println(GUEST_QUESTION_TWO);
        this.answerTwo = scanner.nextLine();
        System.out.println(GUEST_QUESTION_THREE);
        this.answerThree = scanner.nextLine();
    }

    @Override
    public void handle() {
        try {
            answerOne = Integer.toString(scholarshipService.genderParameterMapping(answerOne));
            answerTwo = Integer.toString(scholarshipService.academicParameterMapping(answerTwo));
            answerThree = Integer.toString(scholarshipService.sportsParameterMapping(answerThree));
            printOnConsole(scholarshipService.displayScholarshipsByCriteria(answerOne, answerTwo, answerThree));
            this.result = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            this.result = false;
        }
    }

    private void printOnConsole(List<Scholarship> scholarships) {
        for (Scholarship scheme : scholarships) {
            System.out.print(System.lineSeparator());
            System.out.println(STARS + scheme.getScholarShipName());
            System.out.println(EFFECTIVE_DATE + scheme.getEffectiveDate());
            System.out.println(
                    AMOUNT_GRANT + scheme.getTuitionAmount());
            System.out.println(
                    GIRL_CHILD_SPECIFIC + scheme.getCriteriaGirlChild());
            System.out.println(
                    ACADEMIC_SPECIFIC + scheme.getCriteriaAcademics());
            System.out
                    .println(SPORTS_SPECIFIC + scheme.getCriteriaSports());
        }
        System.out.print(System.lineSeparator());
    }

    @Override
    public void setNextCommand() {
        this.nextCommand = this.result ? HOME.getCommand() : ERROR.getCommand();
    }
}
