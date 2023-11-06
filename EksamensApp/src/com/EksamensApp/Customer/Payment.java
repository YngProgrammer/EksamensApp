package com.EksamensApp.Customer;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.*;
import com.desktopAppExamen.connection.DataBaseConnection; // Import the database connection class

public class Payment {
    private String checkNr;
    private date paymentDate;
    private decimal amount;

    public Payment(String checkNr, date paymentDate, decimal amount){
        this.checkNr = checkNr;
        this.paymentDate = paymentDate;
        this.amount = amount;
    }
    public String getcheckNr(){
        return checkNr;
    }
    public date paymentDate(){
        return paymentDate;
    }
    public decimal amount(){
        return amount;
    }
    public void setCheckNr(String checkNr){
        this.checkNR = checkNr;
    }
    public void setPaymentDate(Date paymentDate){
        this.paymentDate = paymentDate;
    }
    public void setAmount(decimal amount){
        this.amount = amount;
    }
}
