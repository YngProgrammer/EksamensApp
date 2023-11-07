/**
 * 
 * File: Payment.java
 * Description: This is an entity class for "Payment" and encapsulates the details of a payment, including the date of payment and the amount.
 * The payment data comes from 3rd-party. 
 * @author Kim
 * @version 06.11.2023
*/ 


package com.desktopAppExamen.ModelPack;

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
