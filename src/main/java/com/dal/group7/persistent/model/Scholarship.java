package com.dal.group7.persistent.model;


import org.json.JSONObject;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public class Scholarship {
    private Integer id;
    protected String scholarShipName;
    protected Date effectiveDate;
    protected Double tuitionAmount;
    protected Double livingExpenseAmount;
    protected Double travelAmount;
    protected Double insuranceAmount;
    protected Boolean criteriaGirlChild;
    protected Boolean criteriaSports;
    protected Boolean criteriaAcademics;

    public Scholarship() {
    }

    public Scholarship(Integer id, String scholarShipName, Date effectiveDate, Double tuitionAmount,
                       Double livingExpenseAmount, Double travelAmount, Double insuranceAmount,
                       Boolean criteriaGirlChild, Boolean criteriaSports, Boolean criteriaAcademics) {
        this.id = id;
        this.scholarShipName = scholarShipName;
        this.effectiveDate = effectiveDate;
        this.tuitionAmount = tuitionAmount;
        this.livingExpenseAmount = livingExpenseAmount;
        this.travelAmount = travelAmount;
        this.insuranceAmount = insuranceAmount;
        this.criteriaGirlChild = criteriaGirlChild;
        this.criteriaSports = criteriaSports;
        this.criteriaAcademics = criteriaAcademics;
    }

    public Integer getId() {
        return id;
    }

    public String getScholarShipName() {
        return scholarShipName;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public Double getTuitionAmount() {
        return tuitionAmount;
    }

    public Boolean getCriteriaGirlChild() {
        return criteriaGirlChild;
    }

    public Boolean getCriteriaSports() {
        return criteriaSports;
    }

    public Boolean getCriteriaAcademics() {
        return criteriaAcademics;
    }

    public Double getLivingExpenseAmount() {
        return livingExpenseAmount;
    }

    public Double getTravelAmount() {
        return travelAmount;
    }

    public Double getInsuranceAmount() {
        return insuranceAmount;
    }

    public Scholarship from(JSONObject jsonObject) {
        this.id = new Random().nextInt();
        this.scholarShipName = jsonObject.getString("scholarship_name");
        this.effectiveDate =
                Date.valueOf(jsonObject.getString("effective_date"));
        this.tuitionAmount = jsonObject.getDouble("tuition_amount");
        this.insuranceAmount = jsonObject.getDouble("insurance_amount");
        this.travelAmount = jsonObject.getDouble("travel_amount");
        this.livingExpenseAmount = jsonObject.getDouble("living_expenses_amount");
        this.criteriaGirlChild =
                jsonObject.getBoolean("scholarship_criteria_girl_child");
        this.criteriaSports =
                jsonObject.getBoolean("scholarship_criteria_sports");
        this.criteriaAcademics =
                jsonObject.getBoolean("scholarship_criteria_academics");
        return this;
    }

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

    public Map<Integer, Object> getFieldValues() {
        Map<Integer, Object> fieldMap = new HashMap<>();
        final List<Field> fields = stream(this.getClass().getDeclaredFields()).collect(toList());
        fields.forEach(field -> {
            try {
                fieldMap.put(fields.indexOf(field), field.get(this));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
        return fieldMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scholarship that = (Scholarship) o;
        return  Objects.equals(scholarShipName, that.scholarShipName) &&
                Objects.equals(effectiveDate, that.effectiveDate) &&
                Objects.equals(tuitionAmount, that.tuitionAmount) &&
                Objects.equals(livingExpenseAmount, that.livingExpenseAmount) &&
                Objects.equals(travelAmount, that.travelAmount) &&
                Objects.equals(insuranceAmount, that.insuranceAmount) &&
                Objects.equals(criteriaGirlChild, that.criteriaGirlChild) &&
                Objects.equals(criteriaSports, that.criteriaSports) &&
                Objects.equals(criteriaAcademics, that.criteriaAcademics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scholarShipName, effectiveDate, tuitionAmount, livingExpenseAmount, travelAmount,
                insuranceAmount, criteriaGirlChild, criteriaSports, criteriaAcademics);
    }
}
