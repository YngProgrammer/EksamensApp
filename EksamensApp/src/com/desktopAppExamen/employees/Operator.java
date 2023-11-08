/**
 * 
 * File: Operator.java
 * Description: This is an entity class for "Operator" and enharite everything from the superclass Employee.
 * @author Marziyeh
 * @version 06.11.2023
*/ 
package com.desktopAppExamen.employees;

import com.desktopAppExamen.ModelPack.Employee;

public class Operator extends Employee{

//The operator constructor with same parameters.
   public Operator(int employeeNr, String firstName, String lastName){
       super(employeeNr, firstName, lastName);
   }
}