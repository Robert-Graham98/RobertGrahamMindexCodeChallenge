package com.mindex.challenge.data;
//This class is used when a Rest POST call for compensation is made. It is used to gather the necessary data from the request body
public class CompensationForm {
    private Double salary;
    private String effectiveDate;

    public CompensationForm(){}

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}
