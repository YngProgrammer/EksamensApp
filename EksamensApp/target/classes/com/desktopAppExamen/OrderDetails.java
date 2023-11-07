/**
 * 
 * File: OrderDetails.java
 * Description: This is a "orderDetails" class and encapsulates the details of an order, including order quantities and the unit price. 
 * @author Kim
 * @version 06.11.2023
*/ 

package com.desktopAppExamen.OrderDetails;

public class OrderDetails implements Entity {
   
   //Declaration of private data fields 
   private int quantityOrdered;
   private decimal priceEach;
   private int orderLineNr;

   /**
   * Constructor for OrderDetails class.
   * @param quantityOrdered    The quantity of the product ordered.
   * @param priceEach          The unit price of the product.
   * @param orderLineNr        The order line number.
   */

   public OrderDetails(int quantityOrdered, double priceEach, int orderLineNr) {
      this.quantityOrdered = quantityOrdered;
      this.priceEach = priceEach;
      this.orderLineNr = orderLineNr;
   }

   /**
   * Getter methods for product quantity, unit price, and the order line number.
   * @return q'ty, unit price, order line number.
   */

   public int getQuantityOrdered() {
      return this.quantityOrdered;
   }

   public double getPriceEach() {
      return this.priceEach;
   }

   public int getorderLineNr() {
      return this.orderLineNr;
   }

   /**
   * Setter methods for product quantity, unit price, and the order line number.
   * setting @param quantityOrdered, @param priceEach, @param orderLineNr   
   */

   public void setQuantityOrdered(int quantityOrdered){
        this.quantityOrdered = quantityOrdered;
    }
   public void setPriceEach(double priceEach){
        this.priceEach = priceEach;
    }
   public void setOrderLineNr(int orderLineNr){
        this.orderLineNr = orderLineNr;
    }

   /**
   * Calculation methods for sub total amount
   * @return subtotal     
   */

   public decimal calculateTotalPrice(int quantityOrdered, decimal priceEach) {
      return quantityOrdered * priceEach;
   }
}
