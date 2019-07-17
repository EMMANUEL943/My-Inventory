/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chi.controller;

import chi.dao.UserLogin;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpSession;
import util.HibernateUtil;

/**
 *
 * @author Administrator
 */
@Named(value = "userLoginController")
@RequestScoped
public class UserLoginController {
    private String email;
    private String password;
    private String mgero;
    private static final String positionAdmin = "Admin";
    private static final String positionUser = "User";

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMgero() {
        return mgero;
    }

    public void setMgero(String mgero) {
        this.mgero = mgero;
    }
    public UserLoginController() {
    }
    
    public String saveLogin(){
        UserLogin ul = new UserLogin();
        int admin = ul.checkUsersLogin(email, password, positionAdmin);
        int user = ul.checkUsersLogin(email, password, positionUser);
         if(admin == 0){
             HttpSession session = HibernateUtil.getSession();
			session.setAttribute("email", user);
             return "index.xhtml";
         }else if(user ==0 ){
             return "userIndex.xhtml";        
         }else if(admin != 0 || user != 0)
             
             setMgero("Incorrect Password or email. See the Admin for Proper Registration");
         
        return "users.xhtml";
        
    }
    public String logout() {
        
      HttpSession session = HibernateUtil.getSession();
      session.invalidate();
      setFieldToNull();
      return "users.xhtml";
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
       this.setEmail("");                
       this.setPassword("");
       this.setMgero("");
}
}
