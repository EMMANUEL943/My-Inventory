/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chi.controller;

import chi.pojo.Customers;
import chi.pojo.Users;
import chi.service.CustomerServ;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Administrator
 */
@Named(value = "customerController")
@RequestScoped
public class CustomerController implements Serializable {

     @ManagedProperty(value="#{customerServ}") 

    @Autowired
     private CustomerServ customerServ;

    public CustomerServ getCustomerServ() {
        return customerServ;
    }

    public void setCustomerServ(CustomerServ customerServ) {
        this.customerServ = customerServ;
    }
     
     private String customerId;
     private String customerName;
     private String customerEmail;
     private String customerPhone;
     private String customerAddress;
     private String createdDate =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
     private String mgsError;
     private String userId;
     
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
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
     
    public CustomerController() {
    }
    public String saveCustomer(){                 
        Customers c = new Customers();
        Users u = new Users();
        u.setEmail(userId);
        //Employees e = new Employees(empEmail); 
        c.setCreatedDate(createdDate);
        c.setCustomerAddress(customerAddress);
        c.setCustomerEmail(customerEmail);
        c.setCustomerId(customerId);
        c.setCustomerName(customerName);
        c.setCustomerPhone(customerPhone);
        c.setUsers(u);
       
        
        customerServ.createdCustomers(c);
        setFieldToNull();
        mgsError="Customer Record Created!!!";

        return "customers.xhtml";
}
    public String customerUpdate(){
       Customers c = new Customers();
        Users u = new Users();
         u.setEmail(userId);
        //Employees e = new Employees(empEmail); 
        c.setCreatedDate(createdDate);
        c.setCustomerAddress(customerAddress);
        c.setCustomerEmail(customerEmail);
        c.setCustomerId(customerId);
        c.setCustomerName(customerName);
        c.setCustomerPhone(customerPhone);
        c.setUsers(u);
                
        customerServ.updateCustomers(c);
        setFieldToNull();
        mgsError="Customer Record Updated!!!";

        return "customersEdit.xhtml";
    } 
    public String customerDelete(){
       Customers c = new Customers();
        Users u = new Users();
         u.setEmail(userId);
        //Employees e = new Employees(empEmail); 
        c.setCreatedDate(createdDate);
        c.setCustomerAddress(customerAddress);
        c.setCustomerEmail(customerEmail);
        c.setCustomerId(customerId);
        c.setCustomerName(customerName);
        c.setCustomerPhone(customerPhone);
        c.setUsers(u);
                
        customerServ.deleteCustomers(c);
        setFieldToNull();
        mgsError="Customer Record Deleted!!!";

        return "customersEdit.xhtml";
    }   
    public List<Customers> getCustomerDetails() {            
        Customers c = new Customers();
        Users u = new Users();
        List<Customers> sList = customerServ.listCustomers();
            for(Iterator it = sList.iterator();it.hasNext();) {                    
                c = (Customers)it.next();
                    customerId = c.getCustomerId();
                    customerAddress = c.getCustomerAddress();
                    customerEmail = c.getCustomerEmail();
                    customerName = c.getCustomerName();
                    customerPhone = c.getCustomerPhone();
                    createdDate = c.getCreatedDate();
                    //c.setUsers(u);
                    //userId = c.getUsers().getEmail();        
            }
            return sList;
    }
    public String getCustomerDetailsByID() {
        Customers c = new Customers();
        Users u = new Users();
       try{                                 
        List<Customers> sList = customerServ.listCustomersByID(customerId);
//                System.out.println(supplierId);
            int checkCustomersLogin = customerServ.checkCustomersLogin(customerId);
                if(checkCustomersLogin==0){                   
		for(Iterator it = sList.iterator(); it.hasNext();) {                    
                    c = (Customers)it.next();  
                    customerId = c.getCustomerId();
                    customerAddress = c.getCustomerAddress();
                    customerEmail = c.getCustomerEmail();
                    customerName = c.getCustomerName();
                    customerPhone = c.getCustomerPhone();
                    createdDate = c.getCreatedDate();
                    c.setUsers(u);
                    userId = c.getUsers().getEmail();  
                
        }
             }else if(checkCustomersLogin!=0){
               setMgsError("Customer Record does not Exist!!");                                                          
           } 
       }catch(Exception ex){
           ex.printStackTrace();
       }
		return "customersEdit";
    }
     public void validateEmail(FacesContext fc, UIComponent c, Object value)throws ValidatorException{
      String email = (String) value;
      Pattern mask = null;
      mask = Pattern.compile(".+@.+\\.[a-z]+");
      Matcher matcher = mask.matcher(email);
      if(!matcher.matches())
      {
        FacesMessage message = new FacesMessage();
        message.setDetail("Invalid e-mail ID");
        message.setSummary("Invalid e-mail ID");
        throw new ValidatorException(message);
      }   
     }
    public void setFieldToNull(){
        this.setCreatedDate("");
        this.setCustomerAddress("");
        this.setCustomerEmail("");
        this.setCustomerId("");
        this.setCustomerName("");
        this.setCustomerPhone("");
        this.setUserId("");
        this.setMgsError("");
      
        
    
}
    
//    
//      public static void main(String customers[]){
//        CustomerController daoImp = new CustomerController();
//        Customers c = new Customers();
//        Users u = new Users();
//        String createdDate =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()); 
//        
//        
//        c.setCreatedDate(createdDate);
//        c.setCustomerAddress("no4 goggle");
//        c.setCustomerEmail("bolu@live.com");
//        c.setCustomerId("CS003");
//        c.setCustomerName("CHIZZY");
//        c.setCustomerPhone("090128238833");
//        c.setUsers(u);
//        daoImp.
//        
//         u.setEmail("buchi@gmail.com");               
//        u.setFirstName("david");
//        u.setLastName("Debuger");
//        u.setPasswords("456");        
//        u.setPhone("08099901122");                
//        u.setPosition("Store Manager");                
//        u.setCreatedDate(createdDate);
//        daoImp.createdUsers(u);
        
}
