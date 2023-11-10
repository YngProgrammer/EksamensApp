/**
 * 
 * File: Products.java
 * Description: This is an entity class for "product" and encapsulates the basic information of a product.
 * This class represents information about a product's attributes, such as code, name, scale, vendor, description, stock quantity, buy price, and MSRP.
 * @author Ole
 * @version 07.11.2023
*/ 

package modelPack;

import java.util.*;


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

    
// Business Logic methodes

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
        e.printStackTrace();
        // Handle exceptions and errors here
    }
    
    return products;
}
    


}
