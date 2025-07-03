package application.Models;

import java.sql.Date;

public class productData {

    private Integer id;
    private String productId;
    private String productName;
    private String type;
    private Integer stock;
    private Double price;
    private String status;
    private String image;
    private Date date;
    private Integer quantity;
    private String customer_id;
    private Integer totalSold;
    private String employeeName;

    public productData(Integer id, String productId,
             String productName, String type, Integer stock,
             Double price, String status, String image, Date date) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.type = type;
        this.stock = stock;
        this.price = price;
        this.status = status;
        this.image = image;
        this.date = date;
    }
    
    public productData(Integer id, String productId, String productName, 
            String type, Integer quantity, Double price, String image, Date date){
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.type = type;
        this.price = price;
        this.image = image;
        this.date = date;
        this.quantity = quantity;
        
    }

    public productData(String productId, String productName, int totalSold) {
        this.productId = productId;
        this.productName = productName;
        this.totalSold = totalSold;
    }

	public Integer getId() {
        return id;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }
    
    public String getType(){
        return type;
    }

    public Integer getStock() {
        return stock;
    }

    public Double getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public String getImage() {
        return image;
    }

    public Date getDate() {
        return date;
    }
    
    public Integer getQuantity(){
        return quantity;
    }

    public String getCustomer_id() {
        return customer_id;
    }
    
    public Integer getTotalSold() {
        return totalSold;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }
    
    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}
