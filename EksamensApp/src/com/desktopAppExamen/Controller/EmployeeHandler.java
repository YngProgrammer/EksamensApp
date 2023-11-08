/**
 * File: EmployeeHandler.java
 * Description: This is a class responsible for handling CRUD operations related to employees, including operators and administrators.
 * It provides methods for adding employee, operator, and administrator records in the database, as well as authorization operations.
 * @author Albert
 * @version 08.11.2023
 */
 
package com.desktopAppExamen.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.desktopAppExamen.connection.DataBaseConnection;

public class EmployeeHandler {
    public boolean addEmployeeToDatabase(int employeeNr, String firstName, String lastName, String role, String jobTitle, byte password, String email) {
        return addEmployeeToDatabase("employees", employeeNr, firstName, lastName, role, jobTitle, password, email, false, "", "");
    }

    public boolean addOperatorToDatabase(int employeeNr, String firstName, String lastName, String role, String jobTitle, byte password, String email, boolean canCheckDeliveryStatus, String postalCode) {
        return addEmployeeToDatabase("operators", employeeNr, firstName, lastName, role, jobTitle, password, email, canCheckDeliveryStatus, postalCode, postalCode);
    }

    public boolean addAdministratorToDatabase(int employeeNr, String firstName, String lastName, String role, String jobTitle, byte password, String email, String roles) {
        return addEmployeeToDatabase("administrators", employeeNr, firstName, lastName, role, jobTitle, password, email, false, "", roles);
    }

    private boolean addEmployeeToDatabase(String tableName, int employeeNr, String firstName, String lastName, String role, String jobTitle, byte password, String email, boolean canCheckDeliveryStatus, String postalCode, String roles) {
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement pstm = connection.prepareStatement("INSERT INTO " + tableName + " (employeeNr, firstName, lastName, role, jobTitle, PassWord, email, canCheckDeliveryStatus, postalCode, roles) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            pstm.setInt(1, employeeNr);
            pstm.setString(2, firstName);
            pstm.setString(3, lastName);
            pstm.setString(4, role);
            pstm.setString(5, jobTitle);
            pstm.setByte(6, password);
            pstm.setString(7, email);
            pstm.setBoolean(8, canCheckDeliveryStatus);
            pstm.setString(9, postalCode);
            pstm.setString(10, roles);

            int affectedRows = pstm.executeUpdate();

            return affectedRows > 0; 
        } catch (SQLException e) {
            e.printStackTrace();
            return false; 
        }
    }
}