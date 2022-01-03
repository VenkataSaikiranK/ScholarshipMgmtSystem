package com.dal.group7.persistent.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ScholarshipHandle extends Scholarship {

    public Scholarship formResultSet(ResultSet resultSet) throws
            SQLException {
        this.scholarShipName = resultSet.getString(2);
        this.effectiveDate = resultSet.getDate(3);
        this.tuitionAmount = resultSet.getDouble(4);
        this.insuranceAmount = resultSet.getDouble(5);
        this.travelAmount = resultSet.getDouble(6);
        this.livingExpenseAmount = resultSet.getDouble(7);
        this.criteriaGirlChild = resultSet.getBoolean(8);
        this.criteriaAcademics = resultSet.getBoolean(9);
        this.criteriaSports = resultSet.getBoolean(10);
        return this;
    }

}
