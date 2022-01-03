package com.dal.group7.view.implementations;

import com.dal.group7.persistent.model.UserCredential;
import com.dal.group7.service.implementation.StudentLoginService;
import com.dal.group7.view.interfaces.Command;

import static com.dal.group7.constants.ViewConstants.*;
import static com.dal.group7.view.implementations.CommandFactory.ERROR;
import static com.dal.group7.view.implementations.CommandFactory.STUDENT_HOME;

public class StudentLoginCommand extends Command {
  private String userName;
  private String password;
  private final StudentLoginService studentLoginService;
  private boolean success;
  public StudentLoginCommand(StudentLoginService loginService) {
    this.studentLoginService = loginService;
  }
  @Override
  public void printView() {
    System.out.print(PROMPT_PREFIX + ENTER_USERNAME);
    this.userName = scanner.nextLine();
    System.out.print(PROMPT_PREFIX + PASSWORD);
    this.password = scanner.nextLine();
  }

  @Override
  public void handle() {
    try {
      UserCredential userCredential = studentLoginService.userLogin(userName, password);
      if (userCredential.getIsSoftBlock().equals(YES)) {
        String securityAnswer = askSecQuestion();
        studentLoginService.evaluateSecurityAnswer(securityAnswer);
        studentLoginService.updateUserToSoftBlockToNO();
      }
      this.success = true;
    } catch (Exception exception) {
      System.out.println(PROGRAM_MESSAGE_PREFIX + exception.getMessage() + PROGRAM_MESSAGE_POSTFIX);
      this.success = false;
    }
  }

  private String askSecQuestion() {
    System.out.println(ENTER_SECURITY_DETAILS);
    System.out.println(secQOne);
    String securityAnswer = scanner.nextLine();
    return securityAnswer;
  }

  @Override
  public void setNextCommand() {
    nextCommand = success ? STUDENT_HOME.getCommand() : ERROR.getCommand();
  }
}
