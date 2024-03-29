package com.examapp.models;
/**
 * Description: This class represents a product line and encapsulates its attributes.
 * Author: Ole
 * Version: 2023-11-06
 */
public class ProductLine extends Entity {

    private String productLine;
    private String textDescription;
    private String htmlDescription;
    private String image;

    /**
     * Constructor to create a ProductLine object with the specified attributes.
     *
     * @param productLine     The product line name.
     * @param textDescription The text description of the product line.
     * @param htmlDescription The HTML description of the product line.
     * @param image           The image URL or file path for the product line.
     */


    public ProductLine(String productLine, String textDescription, String htmlDescription, String image) {
        this.productLine = productLine;
        this.textDescription = textDescription;
        this.htmlDescription = htmlDescription;
        this.image = image;
    }

// Setters and getters for each variable

    public String getProductLine() {
        return productLine;
    }

    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }

    public String getTextDescription() {
        return textDescription;
    }

    public void setTextDescription(String textDescription) {
        this.textDescription = textDescription;
    }

    public String getHtmlDescription() {
        return htmlDescription;
    }

    public void setHtmlDescription(String htmlDescription) {
        this.htmlDescription = htmlDescription;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

 
    }

  

