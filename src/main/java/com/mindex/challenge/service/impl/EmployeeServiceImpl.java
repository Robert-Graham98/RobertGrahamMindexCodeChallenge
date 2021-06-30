package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.CompensationForm;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompensationRepository compensationRepository;


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
    public ReportingStructure getReports(String id){//takes in employee ID and returns their id along with the number of reports they have
        Employee employee = employeeRepository.findByEmployeeId(id);
        List<Employee> reports = employee.getDirectReports();
        int count = reports.size();
        if(count>0){//if there are reports it will then check those employees for reports and add them to the count
            for(Employee newEmployee : reports){
                String employeeId = newEmployee.getEmployeeId();
                newEmployee = employeeRepository.findByEmployeeId(employeeId);
                count+=newEmployee.getDirectReports().size();
            }//foreach reports
        }//if count

        //it will then generate a new reporting structure and return it
        ReportingStructure reportingStructure = new ReportingStructure();
        reportingStructure.setEmployee(employee);
        reportingStructure.setNumberOfReports(count);

        return reportingStructure;//returns the reporting structure of this employee
    }

    @Override
    public Compensation readCompensation(String id) {//Takes in an employee ID and returns the compensation for that employee
        Employee employee = employeeRepository.findByEmployeeId(id);
        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }
        return compensationRepository.findByEmployeeId(id);
    }

    @Override
    public Compensation setCompensation(String id, CompensationForm compensationForm) {// Takes in an employee ID and compensation form to update someones compensation
        Employee employee = employeeRepository.findByEmployeeId(id);// first it grabs the employee

        Compensation compensation = new Compensation();//next we create a compensation object and fill it with necessary information
        compensation.setEmployeeId(id);
        compensation.setEmployee(employee);
        compensation.setSalary(compensationForm.getSalary());
        compensation.setEffectiveDate(compensationForm.getEffectiveDate());

        compensationRepository.insert(compensation);//we then insert this object into the compensationRepository

        return compensationRepository.findByEmployeeId(id);
    }



    //This was going to be my attempt at making counting reports recursive so that it would not matter how many layers of reports there are
    //After rereading the instructions this seemed out of scope for it since it just cares about the first employees reports followed by the reports belonging to the next level.
//    private int countReports(List<Employee> reports){
//        int count = reports.size();
//        if(count>0){
//            for(Employee report:reports){
//
//                count+=countReports(report.getDirectReports());
//            }
//        }
//        return count;
//
//    }
}
