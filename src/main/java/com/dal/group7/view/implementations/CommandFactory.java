package com.dal.group7.view.implementations;

import com.dal.group7.service.implementation.*;
import com.dal.group7.view.interfaces.Command;

import static com.dal.group7.persistent.implementations.DaoFactory.*;


public enum CommandFactory {
    CREATE_SCHOLARSHIP {
        @Override
        public Command getCommand() {
            return new CreateScholarshipCommand(ServiceConstants.MINISTRY_SCHOLARSHIP_SERVICE);
        }
    },
    ERROR {
        @Override
        public Command getCommand() {
            return new ErrorCommand();
        }
    },
    GUEST {
        @Override
        public Command getCommand() {
            return new GuestCommand(ServiceConstants.MINISTRY_SCHOLARSHIP_SERVICE);
        }
    },
    HOME {
        @Override
        public Command getCommand() {
            return new HomeCommand();
        }
    },
    MINISTRY_HOME {
        @Override
        public Command getCommand() {
            return new MinistryHomeCommand();
        }
    },
    MINISTRY_LOGIN {
        @Override
        public Command getCommand() {
            return new MinistryLoginCommand(ServiceConstants.MINISTRY_LOGIN_SERVICE);
        }
    },
    QUIT {
        @Override
        public Command getCommand() {
            return new QuitCommand();
        }
    },
    SELECT_LOGIN_USER {
        @Override
        public Command getCommand() {
            return new SelectLoginUserCommand();
        }
    },
    SIGN_UP {
        @Override
        public Command getCommand() {
            return new SignUpCommand();
        }
    },
    STUDENT_SIGNUP {
        @Override
        public Command getCommand() {
            return new StudentSignupCommand(ServiceConstants.STUDENT_SERVICE);
        }
    },
    STUDENT_HOME {
        @Override
        public Command getCommand() {
            return new StudentHomeCommand();
        }
    },
    STUDENT_LOGIN {
        @Override
        public Command getCommand() {
            return new StudentLoginCommand(
                    ServiceConstants.STUDENT_LOGIN_SERVICE);
        }

    }, SAVE_FEEDBACK {
        @Override
        public Command getCommand() {
            return new StudentFeedbackCommand(
                    ServiceConstants.STUDENT_FEEDBACK_SERVICE);
        }
    },
    AWARD_INSTITUTE {
        @Override
        public Command getCommand() {
            return new AwardInstitutesCommand(
                    ServiceConstants.MINISTRY_APPLICATION_SERVICE);
        }
    },
    INSTITUTE_SIGNUP {
        @Override
        public Command getCommand() {
            return new InstituteSignupCommand(
                    ServiceConstants.INSTITUTE_SERVICE);
        }
    },
    INSTITUTE_HOME {
        @Override
        public Command getCommand() {
            return new InstituteHomeCommand();
        }
    },
    APPLY_FOR_SCHEME {
        @Override
        public Command getCommand() {
            return new ApplySchemeCommand(
                    ServiceConstants.APPLY_SCHEME_SERVICE);
        }
    },
    LIST_SCHOLARSHIPS {
        @Override
        public Command getCommand() {
            return new ListScholarshipCommand(
                    ServiceConstants.MINISTRY_SCHOLARSHIP_SERVICE);
        }
    },
    VIEW_APPLICATION_STATUS {
        @Override
        public Command getCommand() {
            return new ViewApplicationStatusCommand(
                    ServiceConstants.APPLY_SCHEME_SERVICE);
        }
    },
    INSTITUTE_LOGIN {
        @Override
        public Command getCommand() {
            return new InstituteLoginCommand(ServiceConstants.LOGIN_SERVICE);
        }
    },
    INSTITUTE_APPROVE_REJECT {
        @Override
        public Command getCommand() {
            return new InstituteDecisionCommand(
                    ServiceConstants.INSTITUTE_APPLICATION_SERVICE);
        }
    },
    MINISTRY_DECISION {
        @Override
        public Command getCommand() {
            return new MinistryDecisionCommand(ServiceConstants.MINISTRY_APPLICATION_SERVICE);
        }
    },
    LIST_APPROVED_APPLICATION {
        @Override
        public Command getCommand() {
            return new ListApprovedApplicationCommand(ServiceConstants.INSTITUTE_APPLICATION_SERVICE);
        }
    },
    LIST_APPLICATION {
        @Override
        public Command getCommand() {
            return new ListApplicationCommand(ServiceConstants.INSTITUTE_APPLICATION_SERVICE);
        }
    },
    LIST_APPLICATION_MINISTRY {
        @Override
        public Command getCommand() {
            return new ListApplicationMinistryCommand(ServiceConstants.INSTITUTE_APPLICATION_SERVICE);
        }
    };

    public abstract Command getCommand();

    private static class ServiceConstants {
        private static final MinistryScholarshipService
                MINISTRY_SCHOLARSHIP_SERVICE = new MinistryScholarshipService(
                SCHOLARSHIP.createDao(), new JsonFileReader());
        private static final MinistryLoginService MINISTRY_LOGIN_SERVICE =
                new MinistryLoginService(
                        USER_CREDENTIALS.createDao(),
                        new PwdEncrypt(ENCRYPTION.createDao()));
        private static final StudentService STUDENT_SERVICE =
                new StudentService(STUDENT.createDao(),
                        new JsonFileReader());
        private static final StudentLoginService STUDENT_LOGIN_SERVICE =
                new StudentLoginService(
                        USER_CREDENTIALS.createDao(),
                        new PwdEncrypt(ENCRYPTION.createDao()),
                        APPLICATION.createDao());
        private static final InstituteService INSTITUTE_SERVICE =
                new InstituteService(INSTITUTE.createDao(),
                        new JsonFileReader());
        private static final StudentSchemeService
                APPLY_SCHEME_SERVICE = new StudentSchemeService(
                USER_CREDENTIALS.createDao(), APPLICATION.createDao(),
                new JsonFileReader());
        private static final InstituteLoginService LOGIN_SERVICE =
                new InstituteLoginService(
                        USER_CREDENTIALS.createDao(),
                        new PwdEncrypt(ENCRYPTION.createDao()));
        private static final InstituteApplicationService
                INSTITUTE_APPLICATION_SERVICE =
                new InstituteApplicationService(APPLICATION.createDao());
        private static final MinistryApplicationService
                MINISTRY_APPLICATION_SERVICE = new MinistryApplicationService(
                APPLICATION.createDao(), SCHOLARSHIP.createDao(),
                STUDENT_FINANCE.createDao(), INSTITUTE.createDao(),
                STUDENT_FEEDBACK.createDao());
        private static final StudentFeedbackService STUDENT_FEEDBACK_SERVICE =
                new StudentFeedbackService(STUDENT_FEEDBACK.createDao(),
                        new JsonFileReader());
    }
}
