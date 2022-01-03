package com.dal.group7.service.interfaces;

import java.io.IOException;
import java.sql.SQLException;


public interface UserService {

    void signup(String filename) throws SQLException, IOException;
}
