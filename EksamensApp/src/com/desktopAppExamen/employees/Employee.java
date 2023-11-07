/**
 * 
 * File: Employee.java
 * Description: This is an entity class for "Employee" and encapsulates the basic information of a employee.
 * This class represents information about a employee's attributes, such as employee number, firstname, lastname , jobtitle, password, email, role, and ...
 * @author Marziyeh
 * @version 06.11.2023
*/ 
package com.desktopAppExamen.Employee;



public class Employee{

     //Private data fields
     private int employeeNr;
     private String firstName;
     private String lastName;
     private String jobTitle;
     private byte PW;
     private String email;
     private String role;
     private int reportsTo;
     private String extension;
     private int officeCode;
     private String territory;

     //Constructor for employee class with parameters.
     public Employee(int employeeNr, String firstName, String lastName){
        this.employeeNr = employeeNr;
        this.firstName = firstName;
        this.lastName = lastName;
     }

     public void createEmployee(){

     }

     public void EditEmployee(){

     }

     public void deleteEmployee(int employeeNr){

     }

     public static searchEmployee(int employeeNr){

     }

      // Getter and setter methods, Some of them don't need to be changed, like firstname, and for those, we only have a getter method.
      //We have a setter method for the fields that can be changed later, such as password.  
     public int getEmployeeNr() {
        return employeeNr;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }


    public String getPW() {
        return PW;
    }

    public void setPW(String PW) {
        this.PW = PW;
    }

    public String getEmail() {
        return email;
    }

    public int getReportsTo() {
        return reportsTo;
    }

    public void setReportsTo(int reportsTo) {
        this.reportsTo= reportsTo;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public int getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(int officeCode) {
        this.officeCode = officeCode;
    }

    public String getTerritory() {
        return territory;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }
}