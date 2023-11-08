/**
 * 
 * File: Products.java
 * Description: This is an entity class for "product" and encapsulates the basic information of a product.
 * This class represents information about a product's attributes, such as code, name, scale, vendor, description, stock quantity, buy price, and MSRP.
 * @author Ole
 * @version 07.11.2023
*/ 

package model;

import java.util.*;


public class Products implements Enitity {


    private String productCode;
    private String productName;
    private String productScale;
    private String productVendor;
    private String productDescription;
    private int quantityInStock;
    private double buyPrice;
    private double msrp;

    /**
     * Constructor to create a Product object with the specified attributes.
     *
     * @param productCode       The product code.
     * @param productName       The product name.
     * @param productScale      The product scale.
     * @param productVendor     The product vendor.
     * @param productDescription The product description.
     * @param quantityInStock   The quantity in stock.
     * @param buyPrice          The buy price.
     * @param msrp              The Manufacturer's Suggested Retail Price.
     */
    public Product(String productCode, String productName, String productScale, String productVendor,
                   String productDescription, int quantityInStock, double buyPrice, double msrp) {
        this.productCode = productCode;
        this.productName = productName;
        this.productScale = productScale;
        this.productVendor = productVendor;
        this.productDescription = productDescription;
        this.quantityInStock = quantityInStock;
        this.buyPrice = buyPrice;
        this.msrp = msrp;
    }

    // Setters and getters for each variable

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductScale() {
        return productScale;
    }

    public void setProductScale(String productScale) {
        this.productScale = productScale;
    }

    public String getProductVendor() {
        return productVendor;
    }   

    public void setProductVendor(String productVendor) {
        this.productVendor = productVendor;
    }

    public String getProductDescription() {
        return productDescription;
    }   

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }   

    public int getQuantityInStock() {
        return quantityInStock;
    }   

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }   

    public double getBuyPrice() {
        return buyPrice;
    }   

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }   

    public double getMsrp() {
        return msrp;
    }   

    public void setMsrp(double msrp) {
        this.msrp = msrp;
    }   

    //Possible methods

    public calculateTotalPrice() {

    }

    public void updateStock(int quantityInStock) {

    }

   public  generateProductReport() {


    }
    

    //

        
    


}
