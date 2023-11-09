package modelPack;
/**
 * Description: This class represents a product line and encapsulates its attributes.
 * Author: Ole
 * Version: 2023-11-06
 */
public class ProductLine extends Entity {

    private String productLine;
    private String textDescription;
    private String htmlDescription;
    private byte[] image;

    /**
     * Constructor to create a ProductLine object with the specified attributes.
     *
     * @param productLine     The product line name.
     * @param textDescription The text description of the product line.
     * @param htmlDescription The HTML description of the product line.
     * @param image           The image URL or file path for the product line.
     */


    public ProductLine(String productLine, String textDescription, String htmlDescription, byte[] image) {
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

    // Suggested methods:
    
    public void updateProductLineDescription(String newTextDescription, String newHtmlDescription) {
        // Update the text and HTML descriptions of the product line.
        this.textDescription = newTextDescription;
        this.htmlDescription = newHtmlDescription;
    }

    public void uploadProductImage(String imagePath) {
        // Upload a new image for the product line.
        this.image = imagePath;
    }

    public boolean isImageAvailable() {
        // Check if an image is available for the product line.
        return image != null && !image.isEmpty();
    }
}
