/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chi.dao;

import chi.pojo.Users;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.inject.Named;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import util.HibernateUtil;

/**
 *
 * @author Administrator
 */
@Named
@Repository("userDao")
public class UserDaoImpl implements UserDao{
    
    private SessionFactory sessionFactory;

    @Autowired        
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public void createdUsers(Users u) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        
        u.setEmail(u.getEmail());                
        u.setFirstName(u.getFirstName());
        u.setLastName(u.getLastName());
        u.setPasswords(u.getPasswords());        
        u.setPhone(u.getPhone());                
        u.setPosition(u.getPosition());
        u.setCreatedDate(u.getCreatedDate());
                
        session.save(u);
        trans.commit();
    }

    @Override
    public void updateUsers(Users u) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        
        u.setEmail(u.getEmail());                
        u.setFirstName(u.getFirstName());
        u.setLastName(u.getLastName());
        u.setPasswords(u.getPasswords());        
        u.setPhone(u.getPhone());                
        u.setPosition(u.getPosition());
        u.setCreatedDate(u.getCreatedDate());
               
        session.update(u);
        trans.commit();
    }

    @Override
    public void deleteUsers(Users u) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();        
        
        u.setEmail(u.getEmail());                
        u.setFirstName(u.getFirstName());
        u.setLastName(u.getLastName());
        u.setPasswords(u.getPasswords());        
        u.setPhone(u.getPhone());                
        u.setPosition(u.getPosition());
        u.setCreatedDate(u.getCreatedDate());
        
        session.delete(u);                         
        trans.commit();
    }

    @Override
    public List<Users> listUsersByID(String emailId) {
        Users eObj = new Users();
        List<Users> eList = new ArrayList<Users>();
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        Query queryObj = session.createQuery("from Users where email = :email").setString("email", emailId);
        eObj = (Users)queryObj.uniqueResult();
        eList = queryObj.list();
//        for(Employees em: eList){
////            System.out.println(em.getEmail());
////            System.out.println(em.getFirstName());            
//        }
        trans.commit();
        return eList;
    }

    @Override
    public List<Users> listUsers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        List<Users> listUsers = session.createQuery("select u from Users u").list();
        
        trans.commit();
        return listUsers;
    }

    @Override
    public int checkUsersLogin(String email_id) {
        int status = 1;
         Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction trans = session.beginTransaction();
         Query q = session.createQuery("from Users where email= :email").setString("email", email_id);			        
         List userList = q.list();
         for(Iterator it = userList.iterator(); it.hasNext();){
         Users emp =(Users)it.next();

         if(emp.getEmail().equals(email_id)){                 
             return status=0;
            }             
         }              
         trans.commit();
         return status;
    }
    
    
     public static void main(String employee[]){
        UserDaoImpl daoImp = new UserDaoImpl();
        Users u = new Users();
//
        String createdDate =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()); 

//////
////     insert        
//        u.setEmail("buchi@gmail.com");               
//        u.setFirstName("david");
//        u.setLastName("Debuger");
//        u.setPasswords("456");        
//        u.setPhone("08099901122");                
//        u.setPosition("Store Manager");                
//        u.setCreatedDate(createdDate);
//        daoImp.createdUsers(u);
//////
////////getEmployeeByID
//////        System.out.println(daoImp.listEmployeeByID("oal@gmail.com"));
//////        System.out.println(daoImp.checkEmployeeLogin("oal@gmail.com"));
//////        
//        //update
      u.setEmail("favour@gmail.com");               
        u.setFirstName("tyu");
        u.setLastName("ola");
        u.setPasswords("123");        
        u.setPhone("08099901122");                
        u.setPosition("Admin");                
        u.setCreatedDate(createdDate);
       daoImp.updateUsers(u);
////        
//////        //delete        
//        u.setEmail("buchi@gmail.com");
//        daoImp.deleteUsers(u);
//////        
//////        //getAllEmployeesDetail
//////        List<Employees> liste = daoImp.listEmployee();
//////        for(Iterator it = liste.iterator(); it.hasNext();){
//////            Employees emp = (Employees) it.next();
//////            System.out.println(emp.getEmail()+ " : "+emp.getFirstName()+ " : "+emp.getLastName());           
//////        }
//////     
    }

}

