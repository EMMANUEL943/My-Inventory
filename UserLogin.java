/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chi.dao;

import chi.pojo.Users;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import util.HibernateUtil;

/**
 *
 * @author Administrator
 */
public class UserLogin {
    
     private SessionFactory sessionFactory;
    
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    Session session = HibernateUtil.getSessionFactory().openSession();  
    
    public int checkUsersLogin(String email_id, String pwd, String position){
             int status = 1;
             Query q = session.createQuery("from Users where email= :email and passwords = :passwords and position = :position").setString("email", email_id).setString("passwords", pwd).setString("position", position);			        
             List userList = q.list();
             for(Iterator it = userList.iterator(); it.hasNext();){
             Users u =(Users)it.next();
                          
             if(u.getEmail().equals(email_id) && u.getPasswords().equals(pwd) && u.getPosition().equals(u.getPosition())){                 
                               
//                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sEmail", emp.getEmail());              
                               
//                 String email_db = emp.getEmail();                                                  
//                 String positiion_db = emp.getPosition();                   
//                                  
//                 System.out.println(email_db);                                 
//                 System.out.println(positiion_db);                 
                                  
                 return status=0;
            }                          
        }    
            return status;
    }
    
        public static void main(String checkEmp[]){
        UserLogin usrLogin = new UserLogin();
            
        System.out.println(usrLogin.checkUsersLogin("favour@gmail.com", "123","Admin"));
              
    }

}
