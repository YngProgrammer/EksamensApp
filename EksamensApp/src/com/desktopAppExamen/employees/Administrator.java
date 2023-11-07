/**
 * 
 * File: Administrator.java
 * Description: This is an entity class for "Administrator" and enharite everything from the oparator.
 * @author Marziyeh
 * @version 06.11.2023
*/ 
package com.desktopAppExamen.employees;

//The administrator constructor with same parameters.
public class Administrator extends Operator{

    public Administrator(int employeeNr, String firstName, String lastName){
        super(employeeNr, firstName, lastName);
}