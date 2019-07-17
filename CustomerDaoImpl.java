/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chi.dao;

import chi.pojo.Customers;
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
@Repository("customerDao")
public class CustomerDaoImpl implements CustomerDao {
     private SessionFactory sessionFactory;

    @Autowired        
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    @Override
    public void createdCustomers(Customers c) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        
        c.setCustomerId(c.getCustomerId());
        c.setUsers(c.getUsers());
        c.setCustomerEmail(c.getCustomerEmail());
        c.setCustomerAddress(c.getCustomerAddress());
        c.setCustomerPhone(c.getCustomerPhone());
        c.setCustomerName(c.getCustomerName());
        c.setCreatedDate(c.getCreatedDate());      
        session.save(c);
        trans.commit();
    }

    @Override
    public void updateCustomers(Customers c) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        
        c.setCustomerId(c.getCustomerId());
        c.setUsers(c.getUsers());
        c.setCustomerEmail(c.getCustomerEmail());
        c.setCustomerAddress(c.getCustomerAddress());
        c.setCustomerPhone(c.getCustomerPhone());
        c.setCustomerName(c.getCustomerName());
        c.setCreatedDate(c.getCreatedDate());      
        session.update(c);
        trans.commit();
    }

    @Override
    public void deleteCustomers(Customers c) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();        
        
        c.setCustomerId(c.getCustomerId());
        c.setUsers(c.getUsers());
        c.setCustomerEmail(c.getCustomerEmail());
        c.setCustomerAddress(c.getCustomerAddress());
        c.setCustomerPhone(c.getCustomerPhone());
        c.setCustomerName(c.getCustomerName());
        c.setCreatedDate(c.getCreatedDate());      
        session.delete(c);
        trans.commit();
    }

    @Override
    public List<Customers> listCustomersByID(String customerId) {
        Customers eObj = new Customers();
        List<Customers> eList = new ArrayList<Customers>();
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        Query queryObj = session.createQuery("from Customers where customerId = :customerId").setString("customerId", customerId);
        eObj = (Customers)queryObj.uniqueResult();
        eList = queryObj.list();
//        for(Employees em: eList){
////            System.out.println(em.getEmail());
////            System.out.println(em.getFirstName());            
//        }
//Query queryObj = session.createQuery("from Users where email = :email").setString("email", emailId);
        trans.commit();
        return eList;
    }

    @Override
    public List<Customers> listCustomers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        List<Customers> listCustomers = session.createQuery("select c from Customers c").list();
        
        trans.commit();
        return listCustomers;
    }

    @Override
    public int checkCustomersLogin(String customerId) {
        int status = 1;
         Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction trans = session.beginTransaction();
         Query q = session.createQuery("from Customers where customerid= :customerId").setString("customerId", customerId);			        
         List userList = q.list();
         for(Iterator it = userList.iterator(); it.hasNext();){
         Customers emp =(Customers)it.next();
         if(emp.getCustomerId().equals(customerId)){
             return status=0;
               }             
             }              
              trans.commit();
              return status;
          }
                 
      
    
    
     public static void main(String customers[]){
        CustomerDaoImpl daoImp = new CustomerDaoImpl();
        Customers c = new Customers();
        Users u = new Users();
        u.setEmail("adeola@gmail.com");
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
        
        
       // update
         c.setCustomerId("CS0045");
         c.setUsers(u);
        c.setCustomerEmail("fevi@gmail.com");
        c.setCustomerAddress("adeleke");
        c.setCustomerPhone("09019283893");
        c.setCustomerName("david cletus" );
        c.setCreatedDate(c.getCreatedDate());  
         daoImp.createdCustomers(c);
         
         
 //delete        
       // c.setCustomerId("CS001");
        //daoImp.deleteCustomers(c);
//          c.setCustomerId("CS002");
//        c.setCustomerEmail("buchi@gmail.com");
//        c.setCustomerAddress("NO2A AFOLARIN STR");
//        c.setCustomerPhone("09019283893");
//        c.setCustomerName("david Donald" );
//        c.setCreatedDate(c.getCreatedDate());  
//         daoImp.deleteCustomers(c);
//////
//getEmployeeByID
        //System.out.println(daoImp.listCustomersByID("CS001"));
        //System.out.println(daoImp.checkCustomersLogin("oal@gmail.com"));
//////        
//        //update
//      u.setEmail("favour@gmail.com");               
//        u.setFirstName("ade");
//        u.setLastName("ola");
//        u.setPasswords("123");        
//        u.setPhone("08099901122");                
//        u.setPosition("Admin");                
//        u.setCreatedDate(createdDate);
//       daoImp.updateUsers(u);
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

