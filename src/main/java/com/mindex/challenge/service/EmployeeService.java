package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;

public interface EmployeeService {
    Employee create(Employee employee);
    Employee read(String id);
    Employee update(Employee employee);
    String reports(String id);
    Compensation readCompensation(String id);
    Compensation setCompensation(String id,Compensation compensation);
}
