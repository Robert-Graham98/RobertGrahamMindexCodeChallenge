package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee create(Employee employee) {
        LOG.debug("Creating employee [{}]", employee);

        employee.setEmployeeId(UUID.randomUUID().toString());
        employeeRepository.insert(employee);

        return employee;
    }

    @Override
    public Employee read(String id) {
        LOG.debug("Creating employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        LOG.debug("Updating employee [{}]", employee);

        return employeeRepository.save(employee);
    }

    //<!----------------------------------------------Code done by Robert Graham below-------------------------------------------------!>
    @Override
    public String reports(String id){//takes in employee ID and returns their id along with the number of reports they have
        Employee employee = employeeRepository.findByEmployeeId(id);

        return "{\"employee ID\":\""+employee.getEmployeeId() +"\",\"Number Of Reports\" : "+employee.getDirectReports().size()+"}";//returns the size of the getDirectReports array
    }

    @Override
    public Compensation readCompensation(String id) {//Takes in an employee ID and returns the compensation for that employee
        Employee employee = employeeRepository.findByEmployeeId(id);
        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }
        return employee.getCompensation();
    }

    @Override
    public Compensation setCompensation(String id, Compensation compensation) {// Takes in an employee ID and compensation object to update someones compensation
        Employee employee = employeeRepository.findByEmployeeId(id);// first it grabs the employee and then deletes this employee from the database
        employeeRepository.deleteByEmployeeId(id);

        employee.setCompensation(compensation);// we then set the compensation and add the employee object back into the database.
        employeeRepository.save(employee);

        return employee.getCompensation();
    }
}
