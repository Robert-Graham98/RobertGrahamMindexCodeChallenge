package com.mindex.challenge.data;
//This class is just a simple structure for employee compensation.
// It consists of a salary, and an effective date. Instances of this class are used in Employee.java to store employees compensation
public class Compensation {
    private double salary;
    private String effectiveDate;

    public Compensation(){

    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}
