/**
 * File: EmployeeHandler.java
 * Description: This is a class responsible for handling CRUD operations related to employees, including operators and administrators.
 * It provides methods for adding employee, operator, and administrator records in the database, as well as authorization of new roles.
 * @author Albert
 * @version 09.11.2023
 */
 
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.DataBaseConnection;

public class EmployeeHandler {
    public boolean addEmployeeToDatabase(int employeeNr, String firstName, String lastName, String role, String jobTitle, String password, String email) {
        return addEmployeeToDatabase("employees", employeeNr, firstName, lastName, role, jobTitle, password, email, false, "", "");
    }

    public boolean addOperatorToDatabase(int employeeNr, String firstName, String lastName, String role, String jobTitle, String password, String email, boolean canCheckDeliveryStatus, String postalCode) {
        return addEmployeeToDatabase("operators", employeeNr, firstName, lastName, role, jobTitle, password, email, canCheckDeliveryStatus, postalCode, "");
    }

    public boolean addAdministratorToDatabase(int employeeNr, String firstName, String lastName, String role, String jobTitle, String password, String email, String roles) {
        return addEmployeeToDatabase("administrators", employeeNr, firstName, lastName, role, jobTitle, password, email, false, "", roles);
    }

    private boolean addEmployeeToDatabase(String tableName, int employeeNr, String firstName, String lastName, String role, String jobTitle, String password, String email, boolean canCheckDeliveryStatus, String postalCode, String roles) {
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement pstm = connection.prepareStatement("INSERT INTO " + tableName + " (employeeNr, firstName, lastName, role, jobTitle, password, email, canCheckDeliveryStatus, postalCode, roles) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            pstm.setInt(1, employeeNr);
            pstm.setString(2, firstName);
            pstm.setString(3, lastName);
            pstm.setString(4, role);
            pstm.setString(5, jobTitle);
            pstm.setString(6, password); // Changed setByte to setString for password
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

    // Authorization logic for an employee's role change
    public boolean authorizeEmployee(int requestingEmployeeNr, int targetEmployeeNr, String newRole) {
        // Logic to check if the requesting employee is an admin
        if (!isAdmin(requestingEmployeeNr)) {
            System.out.println("You do not have permission to authorize roles.");
            return false;
        }

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement pstm = connection.prepareStatement("UPDATE employees SET role = ? WHERE employeeNr = ?")) {
            pstm.setString(1, newRole);
            pstm.setInt(2, targetEmployeeNr);

            int affectedRows = pstm.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean isAdmin(int employeeNr) {
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement pstm = connection.prepareStatement("SELECT role FROM employees WHERE employeeNr = ?")) {
            pstm.setInt(1, employeeNr);

            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    String role = rs.getString("role");
                    return "admin".equalsIgnoreCase(role);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}


