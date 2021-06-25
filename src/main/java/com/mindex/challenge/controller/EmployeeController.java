package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee")
    public Employee create(@RequestBody Employee employee) {
        LOG.debug("Received employee create request for [{}]", employee);

        return employeeService.create(employee);
    }

    @GetMapping("/employee/{id}")
    public Employee read(@PathVariable String id) {
        LOG.debug("Received employee create request for id [{}]", id);

        return employeeService.read(id);
    }

    @PutMapping("/employee/{id}")
    public Employee update(@PathVariable String id, @RequestBody Employee employee) {
        LOG.debug("Received employee create request for id [{}] and employee [{}]", id, employee);

        employee.setEmployeeId(id);
        return employeeService.update(employee);
    }
    //<!----------------------------------------------Code done by Robert Graham below-------------------------------------------------!>
    @GetMapping("/reports/{id}")
    public String reports(@PathVariable String id) {
        LOG.debug("Received employee create request for id [{}]", id);

        return employeeService.reports(id);
    }

    @GetMapping("/compensation/{id}")
    public Compensation readCompensation(@PathVariable String id) {
        LOG.debug("Received employee compensation request for id [{}]", id);

        return employeeService.readCompensation(id);
    }

    @PutMapping("/compensation/{id}")
    public Compensation setCompensation(@PathVariable String id, @RequestBody Compensation compensation){
        LOG.debug("Received request to set compensation of [{}]",compensation);

        return employeeService.setCompensation(id,compensation);
    }


}
