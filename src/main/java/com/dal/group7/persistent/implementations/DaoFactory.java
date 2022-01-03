package com.dal.group7.persistent.implementations;

import com.dal.group7.persistent.interfaces.Dao;
import com.dal.group7.persistent.model.*;

public enum DaoFactory {

    INSTITUTE {
        @Override
        public Dao<Integer, Institute> createDao() {
            return new InstituteDao(connectionManager);
        }
    },
    ENCRYPTION {
        @Override
        public Dao createDao() {
            return new PwdEncryptDao(connectionManager);
        }
    },
    SCHOLARSHIP {
        @Override
        public Dao<Integer, Scholarship> createDao() {
            return new ScholarshipDao(connectionManager);
        }
    },
    STUDENT {
        @Override
        public Dao<Integer, Student> createDao() {
            return new StudentDao(connectionManager);
        }
    },
    STUDENT_FEEDBACK {
        @Override
        public Dao<Integer, StudentFeedback> createDao() {
            return new StudentFeedBackDao(connectionManager);
        }
    },
    USER_CREDENTIALS {
        @Override
        public Dao<String, UserCredential> createDao() {
            return new UserCredentialDao(connectionManager);
        }
    },
    APPLICATION {
        @Override
        public Dao<String, Application> createDao() {
            return new ApplicationDao(connectionManager);
        }
    },
    STUDENT_FINANCE {
        @Override
        public Dao<String, StudentFinance> createDao() {
            return new StudentFinanceDao(connectionManager);
        }
    };

    private static final ConnectionManager connectionManager =
            new ConnectionManager();

    public abstract Dao createDao();
}
