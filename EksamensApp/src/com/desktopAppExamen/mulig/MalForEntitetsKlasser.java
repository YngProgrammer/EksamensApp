/**
 * 
 * File: Products.java
 * Description: This is an entity class for "product" and encapsulates the basic information of a product.
 * This class represents information about a product's attributes, such as code, name, scale, vendor, description, stock quantity, buy price, and MSRP.
 * @author INSERTNAME
 * @version 06.11.2023
*/ 
package NameOfPackage

    Import //Enter imports


public class ForslagTilMal {

    //Enter private data fields

    /**
     * Constructor for Payment class.
     *
     * @param checkNr     The check number.
     * @param paymentDate The payment date.
     * @param amount      The payment amount.
     */
    public MalForEntitetsKlasserl(String checkNr, Date paymentDate, double amount) {
        this.checkNr = checkNr;
        this.paymentDate = paymentDate;
        this.amount = amount;
    }

    /**
     * Get the check number.
     *
     * @return The check number.
     */
    public String getCheckNr() {
        return checkNr;
    }

    /**
     * Get the payment date.
     *
     * @return The payment date.
     */
    public Date getPaymentDate() {
        return paymentDate;
    }

    /**
     * Get the payment amount.
     *
     * @return The payment amount.
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Set the check number.
     *
     * @param checkNr The check number to set.
     */
    public void setCheckNr(String checkNr) {
        this.checkNr = checkNr;
    }

    /**
     * Set the payment date.
     *
     * @param paymentDate The payment date to set.
     */
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    /**
     * Set the payment amount.
     *
     * @param amount The payment amount to set.
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }
}
