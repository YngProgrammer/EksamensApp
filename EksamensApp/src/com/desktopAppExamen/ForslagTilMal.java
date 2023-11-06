/**
 * This class represents a Payment object.
 *
 * @author InsertName
 */
package com.desktopAppExamen.Payments;

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
    private Date paymentDate;
    private BigDecimal amount;

    /**
     * Constructor for Payment class.
     *
     * @param checkNr     The check number.
     * @param paymentDate The payment date.
     * @param amount      The payment amount.
     */
    public Payment(String checkNr, Date paymentDate, BigDecimal amount) {
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
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
