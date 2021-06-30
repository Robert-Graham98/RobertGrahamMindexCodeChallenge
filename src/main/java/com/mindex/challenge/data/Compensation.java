package com.mindex.challenge.data;
//This class is used to keep track of employee compensation
// It consists of a salary, and an effective date. This class is used by CompensationRepository to store instances of this class
public class Compensation {
    private String employeeId;
    private Employee employee;
    private double salary;
    private String effectiveDate;


    public Compensation(){

    }
    public String getEmployeeId(){return employeeId;}

    public void setEmployeeId(String employeeId){this.employeeId = employeeId;}

    public Employee getEmployee() {return employee;}

    public void setEmployee(Employee employee) {this.employee = employee;}

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {this.effectiveDate = effectiveDate;}


}
