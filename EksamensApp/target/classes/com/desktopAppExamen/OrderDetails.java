/**
 * 
 * File: OrderDetails.java
 * Description: This is a "orderDetails" class and encapsulates the details of an order, including order quantities and the unit price. 
 * @author Kim
 * @version 06.11.2023
*/ 

package package com.desktopAppExamen.OrderDetails;


public class OrderDetails implements Entity {
   private int quantityOrdered;
   private double priceEach;
   private int orderLineNr;

   public OrderDetails(int quantityOrdered, double priceEach) {
      this.quantityOrdered = quantityOrdered;
      this.priceEach = priceEach;
   }

   public int getQuantityOrdered() {
      return this.quantityOrdered;
   }

   public double getPriceEach() {
      return this.priceEach;
   }

   public void setQuantityOrdered(int quantityOrdered){
        this.quantityOrdered = quantityOrdered;
    }
    public void setPriceEach(double priceEach){
        this.priceEach = priceEach;
    }


   public double calculateTotalPrice(int quantityOrdered, double priceEach) {
      return quantityOrdered * priceEach;
   }
}
