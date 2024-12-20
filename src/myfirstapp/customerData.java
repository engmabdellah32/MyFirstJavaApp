package myfirstapp;

import java.util.Date;

/**
 *
 * @author Eng. Mohamed
 */
public class customerData {
    
    private Integer customerID;
    private String productType;
    private String productName;
    private String productSize;
    private Integer productQuantity;
    private double productPrice;
    private Date date;
    
    public customerData(Integer customerID,String productName, String productType, String productSize, Integer productQuantity, double productPrice, Date date){
        this.customerID = customerID;
        this.productName = productName;
        this.productType = productType;
        this.productPrice = productPrice;
        this.productSize = productSize;
        this.productQuantity = productQuantity;
        this.date = date;
    }
    
    public Integer getCustomerID(){
        return customerID;
    }
    
    public String getProductName(){
        return productName;
    }
    
    public String getProductType(){
        return productType;
    }
    
    public String getProductSize(){
        return productSize;
    }
    
    public Integer getProductQuantity(){
        return productQuantity;
    }
    
    public double getProductPrice(){
        return productPrice;
    }
    
    public Date getdate(){
        return date;
    }
}
