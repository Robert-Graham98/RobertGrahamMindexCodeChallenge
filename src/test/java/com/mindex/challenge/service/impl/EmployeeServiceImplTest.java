package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeServiceImplTest {

    private String employeeUrl;
    private String employeeIdUrl;

    @Autowired
    private EmployeeService employeeService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        employeeUrl = "http://localhost:" + port + "/employee";
        employeeIdUrl = "http://localhost:" + port + "/employee/{id}";
    }

    @Test
    public void testCreateReadUpdate() {
        Employee testEmployee = new Employee();
        testEmployee.setFirstName("John");
        testEmployee.setLastName("Doe");
        testEmployee.setDepartment("Engineering");
        testEmployee.setPosition("Developer");

        // Create checks
        Employee createdEmployee = restTemplate.postForEntity(employeeUrl, testEmployee, Employee.class).getBody();

        assertNotNull(createdEmployee.getEmployeeId());
        assertEmployeeEquivalence(testEmployee, createdEmployee);


        // Read checks
        Employee readEmployee = restTemplate.getForEntity(employeeIdUrl, Employee.class, createdEmployee.getEmployeeId()).getBody();
        assertEquals(createdEmployee.getEmployeeId(), readEmployee.getEmployeeId());
        assertEmployeeEquivalence(createdEmployee, readEmployee);


        // Update checks
        readEmployee.setPosition("Development Manager");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Employee updatedEmployee =
                restTemplate.exchange(employeeIdUrl,
                        HttpMethod.PUT,
                        new HttpEntity<Employee>(readEmployee, headers),
                        Employee.class,
                        readEmployee.getEmployeeId()).getBody();

        assertEmployeeEquivalence(readEmployee, updatedEmployee);
    }

//<!----------------------------------------------Code done by Robert Graham below-------------------------------------------------!>
    @Test
    public void testReports(){//this is testing the functionality of counting the number of reports belonging to an employee
        //First I create the employee that will have the reports
        Employee test = new Employee();
        test.setFirstName("Robert");
        test.setLastName("Graham");
        test.setPosition("Software Developer");
        test.setDepartment("Engineering");
        ArrayList<Employee> testArray = new ArrayList<Employee>();
        testArray.add(new Employee());
        testArray.add(new Employee());
        testArray.add(new Employee());
        test.setDirectReports(testArray);
        Employee createdEmployee = restTemplate.postForEntity(employeeUrl, test, Employee.class).getBody();

        //after this I retrieved this created employee and compared the number of reports I am expecting to the number I received
        Employee readEmployee = restTemplate.getForEntity(employeeIdUrl, Employee.class, createdEmployee.getEmployeeId()).getBody();
        assertEquals("{\"employee ID\":\""+ readEmployee.getEmployeeId()+"\",\"Number Of Reports\" : 3}",employeeService.reports(readEmployee.getEmployeeId()));
    }

    @Test
    public void testCompensation(){//this is testing the functionality of getting an employees compensation and updating it
        //First we will again create an employee
        Employee test = new Employee();
        test.setFirstName("Robert");
        test.setLastName("Graham");
        test.setPosition("Software Developer");
        test.setDepartment("Engineering");
        Compensation testCompensation = new Compensation();
        testCompensation.setSalary(1245.52);
        testCompensation.setEffectiveDate("7/25/21");
        test.setCompensation(testCompensation);
        Employee createdEmployee = restTemplate.postForEntity(employeeUrl, test, Employee.class).getBody();


        //Check Read Compensation
        Employee readEmployee = restTemplate.getForEntity(employeeIdUrl, Employee.class, createdEmployee.getEmployeeId()).getBody();
        System.out.println(readEmployee.getCompensation());
        assertEquals(createdEmployee.getCompensation().getSalary(),employeeService.readCompensation(readEmployee.getEmployeeId()).getSalary(),0.02);
        assertEquals(createdEmployee.getCompensation().getEffectiveDate(),employeeService.readCompensation(readEmployee.getEmployeeId()).getEffectiveDate());

        //Check update Compensation
        Compensation newCompensation = new Compensation();
        newCompensation.setSalary(5);
        newCompensation.setEffectiveDate("8/1/21");
        createdEmployee.setCompensation(newCompensation);

        employeeService.setCompensation(readEmployee.getEmployeeId(),newCompensation);
        assertEquals(5,employeeService.readCompensation(readEmployee.getEmployeeId()).getSalary(),.02);
        assertEquals("8/1/21",employeeService.readCompensation(readEmployee.getEmployeeId()).getEffectiveDate());

    }

    private static void assertEmployeeEquivalence(Employee expected, Employee actual) {
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getDepartment(), actual.getDepartment());
        assertEquals(expected.getPosition(), actual.getPosition());
    }
}
