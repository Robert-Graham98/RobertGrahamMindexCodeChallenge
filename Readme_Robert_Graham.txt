This file has been updated with the changes I made on 6/30/2021

EmployeeService
- Created Template methods for counting the number of reports belonging to an employee ID, figuring out what their current compensation is, and updating their compensation
- getReports now returns a ReportingStructure object

EmployeeServiceImpl.java
 -getReports 
	- Takes in an employee ID and returns the ReportingStructure object for that employee
 -readCompensation 
	- Takes in an employee ID and returns their current compensation
 -setCompensation
	- Takes in an employee ID and a Compensation form to set their new compensation
EmployeeController.java
 -Added a get at the URL /reports/{id} that takes the given ID and calls the getReports method
 -Added a get at the URL /compensation/{id} that takes the given ID and calls the readCompensation method
 -Added a put at the URL /compensation/{id} that takes the given ID and a compensationForm object in the body and calls the setCompensation method

EmployeeRepository.java 
 - The following information is no longer valid and is kept there to show my progress "added in deleteByEmployeeId to remove a given employee from the database in order to update any necessary data and add them employee back in"

Employee.java
 -Added in an Attribute of compensation which is a compensation object along with getters/setters for this object

Compensation.java
 -This class is used with CompensationRepository to store the compensation for employees along with their effective date. In order to get it working I had to change things slightly from the instructions. I added a field called employee ID that stores the ID for the given employee and basically uses it as a foreign key/primary key

Compensation Repository
- This class stores compensations for employees using Mongo Repository
- the one function of findByEmployee ID functions the same as the one in Employee Repository except this returns a compensation

Compensation Form 
- this has two attributes of effective date and salary and is used when creating a new compensation for an employee



There are also test cases in EmployeeServiceImplTest.java that test the Reports functionality and the Compensation functionality these have also been updated and pass