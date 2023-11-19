/**
 * 
 * File: Products.java
 * Description: This is an entity class for "product" and encapsulates the basic information of a product.
 * This class represents information about a product's attributes, such as code, name, scale, vendor, description, stock quantity, buy price, and MSRP.
 * @author Ole
 * @version 09.11.2023
*/ 

package com.examapp.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.examapp.DataBaseConnection;


public class Products extends Entity {


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
    public Products(String productCode, String productName, String productScale, String productVendor,
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

   /**
    * get the product code
    * @return productCode
    */

    public String getProductCode() {
        return productCode;
    }

    /**
     * set the productCode
     * @param productCode
     */

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
    * get the productName  
    * @return productName
    */

    public String getProductName() {
        return productName;
    }

    /**
     * set the productName
     * @param productName
     */

    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * get the productScale
     * @return productScale
     */
  

    public String getProductScale() {
        return productScale;
    }

    /**
     * set the productScale
     * @param productScale
     */

    public void setProductScale(String productScale) {
        this.productScale = productScale;
    }

    /**
     * get the productVendor
     * @return productVendor
     */ 

    public String getProductVendor() {
        return productVendor;
    }

    /**
     * set the productVendor
     * @param productVendor
     */

    public void setProductVendor(String productVendor) {
        this.productVendor = productVendor;
    }

    /**
     * get the productDescription
     * @return productDescription
     */

    public String getProductDescription() {
        return productDescription;
    } 
    
    /**
     * set the productDescription
     * @param productDescription
     */


    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }   

    /**
     * get the quantityInStock
     * @return quantityInStock
     */


    public int getQuantityInStock() {
        return quantityInStock;
    } 
    
    /**
     * set the quantityInStock
     * @param quantityInStock
     */

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }   

    /**
     * get the buyPrice
     * @return buyPrice
     */

    public double getBuyPrice() {
        return buyPrice;
    }   

    /**
     * set the buyPrice
     * @param buyPrice
     */

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }   

    /**
     * get the msrp
     * @return msrp
     */

    public double getMsrp() {
        return msrp;
    }  
    
    /**
     * set the msrp
     * @param msrp
     */

    public void setMsrp(double msrp) {
        this.msrp = msrp;
    }   

    
// Business Logic methodes

    // Business Logic methodes

public List<Products> getProducts() {
    List<Products> products = new ArrayList<>();

    try (Connection connection = DataBaseConnection.getConnection()) {
        String sql = "SELECT * FROM products";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Products product = new Products(
                resultSet.getString("productCode"),
                resultSet.getString("productName"),
                resultSet.getString("productScale"),
                resultSet.getString("productVendor"),
                resultSet.getString("productDescription"),
                resultSet.getInt("quantityInStock"),
                resultSet.getDouble("buyPrice"),
                resultSet.getDouble("msrp")
            );
            products.add(product);
        }
    } catch (SQLException e) {
        System.out.println("Failed to retrieve products. Please try again later or contact support.");
        e.printStackTrace();
    }

    return products;
}


  public List<Products> searchProducts(String searchCriteria) {
    List<Products> products = new ArrayList<>();
    
    try (Connection connection = DataBaseConnection.getConnection()) {
        String sql = "SELECT * FROM products WHERE productName LIKE ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "%" + searchCriteria + "%"); // Assuming search by product name

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            // Create a new 'Products' object for each result and add it to the list
            Products product = new Products(
                resultSet.getString("productCode"),
                resultSet.getString("productName"),
                resultSet.getString("productScale"),
                resultSet.getString("productVendor"),
                resultSet.getString("productDescription"),
                resultSet.getInt("quantityInStock"),
                resultSet.getDouble("buyPrice"),
                resultSet.getDouble("msrp")
            );
            products.add(product);
        }
    } catch (SQLException e) {
        System.out.println("No product found.");
        e.printStackTrace();
    }
    
    return products;
}
    


}

