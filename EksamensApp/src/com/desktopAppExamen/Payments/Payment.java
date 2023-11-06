
/**
 * 
 * File: Payment.java
 * Description: This is an entity class for "Payment" and encapsulates the details of a payment, including the date of payment and the amount.
 * The payment data comes from 3rd-party. 
 * @author Kim
 * @version 06.11.2023
*/ 


package com.desktopAppExamen.Payments;

import java.math.BigDecimal;
// Import the database connection class
import java.sql.*;

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
