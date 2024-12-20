package myfirstapp;

import java.sql.Date;

/**
 *
 * @author Eng. Mohamed
 */
public class ProductData {
    private int productID;
    private String productName;
    private String productType;
    private String availableSize;
    private String status;
    private double price;
    private String image;
    private Date date;
    
    public ProductData(int productID, String productName, String productType, String availableSize, String status,
                        double price, String image, Date date){
      this.productID = productID;
      this.productName = productName;
      this.productType = productType;
      this.availableSize = availableSize;
      this.status = status;
      this.price = price;
      this.image = image;
      this.date = date;
            
    }
    
    public int getProductID(){
        return productID;
    }
    
    public String getProductName(){
        return productName;
    }
    
    public String getProductType(){
        return productType;
    }
    
    public String getAvailableSize(){
        return availableSize;
    }
    
    public String getStatus(){
        return status;
    }
    
    public double getPrice(){
        return price;
    }
    
    public String getImage(){
        return image;
    }
    
    public Date getDate(){
        return date;
    }
    
    
    
    
    
    
}



