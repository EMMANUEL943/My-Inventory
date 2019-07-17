/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chi.controller;

import chi.dao.UserLogin;
import chi.pojo.Product;
import chi.pojo.Suppliers;
import chi.pojo.Users;
import chi.service.UserServ;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Administrator
 */
@Named(value = "userController")
@RequestScoped
public class UserController implements Serializable{

   @ManagedProperty(value="#{userServ}") 

    @Autowired
    private UserServ userServ;

    public UserServ getUserServ() {
        return userServ;
    }

    public void setUserServ(UserServ userServ) {
        this.userServ = userServ;
    }
   
     private String email;
     private String firstName;
     private String lastName;
     private String passwords;
     private String phone;
     private String position;
     private String createdDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
     private String mgsError;
 
    public String getMgsError() {
        return mgsError;
    }

    public void setMgsError(String mgsError) {
        this.mgsError = mgsError;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
 
    public UserController() {
    }
     
    UserLogin ul = new UserLogin();
    
   public String save(){ 
       
        Users u = new Users();                
        u.setEmail(email);        
        u.setFirstName(firstName);
        u.setLastName(lastName);
        u.setPasswords(passwords);        
        u.setPhone(phone);        
        u.setPosition(position); 
        u.setCreatedDate(createdDate);
        
        try{                    
        int message = ul.checkUsersLogin(email, passwords, position);         
        if(message !=0){     
            userServ.createdUsers(u);
            setFieldToNull();            
            mgsError="User Record Created!!!"; 
            setFieldToNull(); 
        }else if(message == 0){            
             mgsError="User Record Already Exist!!!";             
        }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return "/userReg.xhtml"; 
    }
   public String updateUsers(){
        Users u = new Users();  
      
        int mgs = userServ.checkUsersLogin(email);
        if(mgs ==0){                     
       u.setEmail(email);        
        u.setFirstName(firstName);
        u.setLastName(lastName);
        u.setPasswords(passwords);        
        u.setPhone(phone);        
        u.setPosition(position); 
        u.setCreatedDate(createdDate);

            userServ.updateUsers(u);
            setFieldToNull();
            mgsError="User Record Updated!!!";                      
           }
            else if(mgs !=0){
            mgsError="User Record Does Not Exist!!!"; 
        }
        return "userEdit";
    }
     public String deleteUsers(){
        Users u = new Users();  
    
        int mgs = userServ.checkUsersLogin(email);
        if(mgs ==0){                     
         u.setEmail(email);        
        u.setFirstName(firstName);
        u.setLastName(lastName);
        u.setPasswords(passwords);        
        u.setPhone(phone);        
        u.setPosition(position);        
        u.setCreatedDate(createdDate);

            userServ.deleteUsers(u);
            setFieldToNull();
            mgsError="User Record Deleted!!!";                      
           }
            else if(mgs !=0){
            mgsError="User Record Does Not Exist!!!"; 
        }
        return "userEdit";
    }
     public String getUserDetailsByID() {
         Users u = new Users();
         
       
       try{                                 
        List<Users> sList = userServ.listUsersByID(email);
//                System.out.println(supplierId);
            int checkUserLogin = userServ.checkUsersLogin(email);
                if(checkUserLogin==0){                   
		for(Iterator it = sList.iterator(); it.hasNext();) {                    
                    u = (Users)it.next();  
                    email = u.getEmail();
                    firstName = u.getFirstName();
                    lastName = u.getLastName();
                    passwords = u.getPasswords();
                    createdDate = u.getCreatedDate();
                    phone = u.getPhone();
                    position = u.getPosition();
                
        }
             }else if(checkUserLogin!=0){
               setMgsError("User Record does not Exist!!");                                                          
           } 
       }catch(Exception ex){
           ex.printStackTrace();
       }
		return "userEdit";
    }
        public List<Users> getUserDetails() {            
        Users u = new Users();
        
        List<Users> sList = userServ.listUsers();
            for(Iterator it = sList.iterator();it.hasNext();) {                    
                u = (Users)it.next();
                    email = u.getEmail();
                    firstName = u.getFirstName();
                    lastName = u.getLastName();
                    passwords = u.getPasswords();
                    createdDate = u.getCreatedDate();
                    phone = u.getPhone();
                    position = u.getPosition();
            }
            return sList;
        }
    public void setFieldToNull(){
        this.setEmail("");                
        this.setFirstName("");
        this.setLastName("");
        this.setMgsError("");
        this.setPasswords("");        
        this.setPhone("");        
        this.setPosition("");
        this.setCreatedDate("");
}
}
