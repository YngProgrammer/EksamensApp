package com.desktopAppExamen.Payments;

import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
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
    private Date paymentDate;
    private BigDecimal amount;

    public Payment(String checkNr, Date paymentDate, BigDecimal amount){
        this.checkNr = checkNr;
        this.paymentDate = paymentDate;
        this.amount = amount;
    }
    public String getcheckNr(){
        return checkNr;
    }
    public Date paymentDate(){
        return paymentDate;
    }
    public BigDecimal amount(){
        return amount;
    }
    public void setCheckNr(String checkNr){
        this.checkNr = checkNr;
    }
    public void setPaymentDate(Date paymentDate){
        this.paymentDate = paymentDate;
    }
    public void setAmount(BigDecimal amount){
        this.amount = amount;
    }
}
