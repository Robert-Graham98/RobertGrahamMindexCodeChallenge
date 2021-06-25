This file will simply explain the changes I have made among each file to aid in going through my work.

EmployeeService
- Created Template methods for counting the number of reports belonging to an employee ID, figuring out what their current compensation is, and updating their compensation

EmployeeServiceImpl.java
 -Reports 
	- Takes in an employee ID and returns the employee id along with the number of reports belonging to that ID
 -readCompensation 
	- Takes in an employee ID and returns their current compensation
 -setCompensation
	- Takes in an employee ID and a Compensation object and updates the compensation for the given employee

EmployeeController.java
 -Added a get at the URL /reports/{id} that takes the given ID and calls the reports method
 -Added a get at the URL /compensation/{id} that takes the given ID and calls the readCompensation method
 -Added a put at the URL /compensation/{id} that takes the given ID and the compensation object in the body and calls the setCompensation method

EmployeeRepository.java 
 -Added in deleteByEmployeeId to remove a given employee from the database in order to update any necessary data and add them employee back in

Employee.java
 -Added in an Attribute of compensation which is a compensation object along with getters/setters for this object

Compensation.java
 -Is a simple class that just stores the salary and effective date for an employees compensation



There are also test cases in EmployeeServiceImplTest.java that test the Reports functionality and the Compensation functionality