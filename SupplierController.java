/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chi.controller;

import chi.pojo.Suppliers;
import chi.pojo.Users;
import chi.service.SupplierServ;
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
@Named(value = "supplierController")
@RequestScoped
public class SupplierController implements Serializable {

     @ManagedProperty(value="#{supplierServ}") 

    @Autowired
     private SupplierServ supplierServ;

    public SupplierServ getSupplierServ() {
        return supplierServ;
    }

    public void setSupplierServ(SupplierServ supplierServ) {
        this.supplierServ = supplierServ;
    }
    
     private String supplierId;
     private String supplierName;
     private String supplierEmail;
     private String supplierPhone;
     private String companyName;
     private String createdDate  =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
     private String mgsError;
     private String userId;
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
   

    public String getMgsError() {
        return mgsError;
    }

    public void setMgsError(String mgsError) {
        this.mgsError = mgsError;
    }
    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }
    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public String getSupplierPhone() {
        return supplierPhone;
    }

    public void setSupplierPhone(String supplierPhone) {
        this.supplierPhone = supplierPhone;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
     
    public SupplierController() {
    }
    public String saveSupplier(){                 
        Suppliers s = new Suppliers();
        Users u = new Users();
        u.setEmail(userId);
        //Employees e = new Employees(empEmail); 
        s.setCompanyName(companyName);
        s.setCreatedDate(createdDate);
        s.setSupplierEmail(supplierEmail);
        s.setSupplierName(supplierName);
        s.setSupplierPhone(supplierPhone);
        s.setUsers(u);
        s.setSupplierId(supplierId);
        
        supplierServ.createdSuppliers(s);
        setFieldToNull();
        mgsError="Supplier Record Created!!!";

        return "suppliers.xhtml";  
    }
    public String updateSuppliers(){
       Suppliers s = new Suppliers();
         Users u = new Users();
         u.setEmail(userId);
        //Employees e = new Employees(empEmail); 
        s.setCompanyName(companyName);
        s.setCreatedDate(createdDate);
        s.setSupplierEmail(supplierEmail);
        s.setSupplierName(supplierName);
        s.setSupplierPhone(supplierPhone);
        s.setUsers(u);
        s.setSupplierId(supplierId);
                
        supplierServ.updateSuppliers(s);
        setFieldToNull();
        mgsError="Supplier Record Updated!!!";

         return "suppliersEdit";
    } 
    public String deleteSuppliers(){
       Suppliers s = new Suppliers();
         Users u = new Users();
         u.setEmail(userId);
        //Employees e = new Employees(empEmail); 
        s.setCompanyName(companyName);
        s.setCreatedDate(createdDate);
        s.setSupplierEmail(supplierEmail);
        s.setSupplierName(supplierName);
        s.setSupplierPhone(supplierPhone);
        s.setUsers(u);
        s.setSupplierId(supplierId);
                
        supplierServ.deleteSuppliers(s);
        setFieldToNull();
        mgsError="Supplier Record Deleted!!!";

         return "suppliersEdit";
    } 
    public List<Suppliers> getSupplierDetails() {            
        Suppliers s = new Suppliers();
        Users u = new Users();
        List<Suppliers> sList = supplierServ.listSuppliers();
            for(Iterator it = sList.iterator();it.hasNext();) {                    
                s = (Suppliers)it.next();
                    supplierId = s.getSupplierId();
                    supplierPhone = s.getSupplierPhone();
                    supplierName = s.getSupplierName();
                    supplierEmail = s.getSupplierEmail();
                    companyName = s.getCompanyName();
                    createdDate = s.getCreatedDate();
                    //c.setUsers(u);
                    //userId = c.getUsers().getEmail();        
            }
            return sList;
    }
    public String getSupplierDetailsByID() {
        Suppliers s = new Suppliers();
         Users u = new Users();
       try{                                 
        List<Suppliers> sList = supplierServ.listSuppliersByID(supplierId);
//                System.out.println(supplierId);
            int checkSuppliersLogin = supplierServ.checkSuppliersLogin(supplierId);
                if(checkSuppliersLogin==0){                   
		for(Iterator it = sList.iterator(); it.hasNext();) {                    
                    s = (Suppliers)it.next();        
                    supplierId = s.getSupplierId();
                    supplierPhone = s.getSupplierPhone();
                    supplierName = s.getSupplierName();
                    supplierEmail = s.getSupplierEmail();
                    companyName = s.getCompanyName();
                    createdDate = s.getCreatedDate();
                    s.setUsers(u);
                    userId = s.getUsers().getEmail();  
                
        }
             }else if(checkSuppliersLogin!=0){
               setMgsError("Supplier Record does not Exist!!");                                                          
           } 
       }catch(Exception ex){
           ex.printStackTrace();
       }
		return "suppliersEdit";
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
       this.setCompanyName("");
       this.setCreatedDate("");
       this.setSupplierEmail("");
       this.setSupplierId("");
       this.setSupplierName("");
       this.setSupplierPhone(""); 
       this.setMgsError("");
       this.setUserId("");
}
}