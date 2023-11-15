
/**
 * File: CustomerHandler.java
 * Description:
 * The CustomerHandler class is responsible for managing customer records in our CMS.
 * It provides methods for adding, editing, deleting, and generating customer reports, interfacing with a database.
 * @author Albert
 * @version 09.11.2023
 */

package controller;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import connection.DataBaseConnection;
import model.Customer;
import model.Order;


public class CustomerHandler {

	/*hvis du vil teste
	 * public static void main(String[] args) {
	        SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                try 
	                {
	                	Customer customer1 = new Customer( 1, "testh" , "a",  "b",  2,  null);
	                	addCustomer( 1, "testh" , "a",  "b",  2,  null);
	                	System.out.println(customer1);	   
	                	} catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        });
	    }
	*/
	
    public static boolean addCustomer(int customerNr, String companyName, String contactLastName, String contactFirstName, int salesRepEmployeeNr, BigDecimal creditLimit) {
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement pstm = connection.prepareStatement("INSERT INTO customers (customerNumber, customerName, contactLastName, contactFirstName, salesRepEmployeeNumber, creditLimit) VALUES (?, ?, ?, ?, ?, ?, )")) {
            pstm.setInt(1, customerNr);
            pstm.setString(2, companyName);
            pstm.setString(3, contactLastName);
            pstm.setString(4, contactFirstName);
            pstm.setInt(5, salesRepEmployeeNr);
            pstm.setBigDecimal(6, creditLimit);

            int affectedRows = pstm.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editCustomer(int customerNr, String companyName, String contactLastName, String contactFirstName, int salesRepEmployeeNr, BigDecimal creditLimit) {
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement pstm = connection.prepareStatement("UPDATE customers SET companyName = ?, contactLastName = ?, contactFirstName = ?, salesRepEmployeeNr = ?, creditLimit = ? WHERE customerNr = ?")) {
            pstm.setString(1, companyName);
            pstm.setString(2, contactLastName);
            pstm.setString(3, contactFirstName);
            pstm.setInt(4, salesRepEmployeeNr);
            pstm.setBigDecimal(5, creditLimit);
            pstm.setInt(6, customerNr);

            int affectedRows = pstm.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCustomer(int customerNr) {
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement pstm = connection.prepareStatement("DELETE FROM customers WHERE customerNr = ?")) {
            pstm.setInt(1, customerNr);

            int affectedRows = pstm.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Customer> generateCustomerReport() {
        List<Customer> customerList = new ArrayList<>();
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement pstm = connection.prepareStatement("SELECT * FROM customers")) {
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    int customerNr = rs.getInt("customerNr");
                    String companyName = rs.getString("companyName");
                    String contactLastName = rs.getString("contactLastName");
                    String contactFirstName = rs.getString("contactFirstName");
                    int salesRepEmployeeNr = rs.getInt("salesRepEmployeeNr");
                    BigDecimal creditLimit = rs.getBigDecimal("creditLimit");

                    Customer customer = new Customer(customerNr, companyName, contactLastName, contactFirstName, salesRepEmployeeNr, creditLimit);
                    customerList.add(customer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerList;
    }
}


