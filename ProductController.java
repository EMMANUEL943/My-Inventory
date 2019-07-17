/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chi.controller;

import chi.pojo.Product;
import chi.pojo.Suppliers;
import chi.service.ProductServ;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import org.springframework.beans.factory.annotation.Autowired;


@Named(value = "productController")
@RequestScoped
public class ProductController implements Serializable {

     @ManagedProperty(value="#{productServ}") 

    @Autowired
     private ProductServ productServ;

    public ProductServ getProductServ() {
        return productServ;
    }

    public void setProductServ(ProductServ productServ) {
        this.productServ = productServ;
    }
     private String productId;  
    // private Suppliers suppliers;
     private String productName;
     private Integer nafdacNumber;
     private Double unitPrice;
     private Integer quantity;
     private Double totalPrice;
     private String manufacturedDate;
     private String expireDate;
     private String createdDate =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
     private String mgsError;
    // private String suppId;

   
     
     

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getNafdacNumber() {
        return nafdacNumber;
    }

    public void setNafdacNumber(Integer nafdacNumber) {
        this.nafdacNumber = nafdacNumber;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getManufacturedDate() {
        return manufacturedDate;
    }

    public void setManufacturedDate(String manufacturedDate) {
        this.manufacturedDate = manufacturedDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getMgsError() {
        return mgsError;
    }

    public void setMgsError(String mgsError) {
        this.mgsError = mgsError;
    }

  
    public ProductController(){
        
    }
    
    public String saveProduct(){                 
        Product p = new Product();
        //Suppliers s = new Suppliers();
      
        //Employees e = new Employees(empEmail); 
        p.setCreatedDate(createdDate);
        p.setExpireDate(expireDate);
        p.setManufacturedDate(manufacturedDate);
        p.setNafdacNumber(nafdacNumber);
        p.setProductId(productId);
        p.setProductName(productName);
        p.setQuantity(quantity);
        p.setTotalPrice(totalPrice);
        p.setUnitPrice(unitPrice); 
       // p.setSuppliers(s);
       // p.setSuppliers(suppliers);
       
        
        productServ.createdProduct(p);
        setFieldToNull();
        mgsError="Product Created!!!";

        return "product.xhtml";
    }
    public void setFieldToNull(){
        this.setCreatedDate("");
        this.setExpireDate("");
        this.setManufacturedDate("");
        this.setNafdacNumber(0);
        this.setProductId("");
        this.setProductName("");
        this.setQuantity(0);
        this.setTotalPrice(0.0);
        this.setUnitPrice(0.0);
        //this.setSuppliers(null);
    }
}
     