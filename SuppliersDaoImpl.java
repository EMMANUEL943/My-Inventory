/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chi.dao;

import chi.pojo.Suppliers;
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
@Repository("suppliersDao")
public class SuppliersDaoImpl implements SuppliersDao{
     private SessionFactory sessionFactory;

    @Autowired        
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public void createdSuppliers(Suppliers s) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        
        s.setCompanyName(s.getCompanyName());
        s.setUsers(s.getUsers());
        s.setCreatedDate(s.getCreatedDate());
        s.setSupplierEmail(s.getSupplierEmail());
        s.setSupplierId(s.getSupplierId());
        s.setSupplierName(s.getSupplierName());
        s.setSupplierPhone(s.getSupplierPhone());
        s.setCreatedDate(s.getCreatedDate());
        session.persist(s);
        trans.commit();
    }
   
    @Override
    public void updateSuppliers(Suppliers s) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        
        s.setCompanyName(s.getCompanyName());
        s.setUsers(s.getUsers());
        s.setCreatedDate(s.getCreatedDate());
        s.setSupplierEmail(s.getSupplierEmail());
        s.setSupplierId(s.getSupplierId());
        s.setSupplierName(s.getSupplierName());
        s.setSupplierPhone(s.getSupplierPhone());
        s.setCreatedDate(s.getCreatedDate());
        session.update(s);
        trans.commit();
    }

   @Override
    public void deleteSuppliers(Suppliers s) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        
        s.setCompanyName(s.getCompanyName());
        s.setUsers(s.getUsers());
        s.setCreatedDate(s.getCreatedDate());
        s.setSupplierEmail(s.getSupplierEmail());
        s.setSupplierId(s.getSupplierId());
        s.setSupplierName(s.getSupplierName());
        s.setSupplierPhone(s.getSupplierPhone());
        s.setCreatedDate(s.getCreatedDate());
        session.delete(s);
        trans.commit();
    }

    @Override
    public List<Suppliers> listSuppliersByID(String supplierId) {
        Suppliers eObj = new Suppliers();
        List<Suppliers> eList = new ArrayList<Suppliers>();
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        Query queryObj = session.createQuery("from Suppliers where supplierId = :supplierId").setString("supplierId", supplierId);
        eObj = (Suppliers)queryObj.uniqueResult();
        eList = queryObj.list();
//        for(Employees em: eList){
////            System.out.println(em.getEmail());
////            System.out.println(em.getFirstName());            
//        }
        trans.commit();
        return eList;
    }

    @Override
    public List<Suppliers> listSuppliers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        List<Suppliers> listSuppliers = session.createQuery("select s from Suppliers s").list();
        
        trans.commit();
        return listSuppliers;
    }

    @Override
    public int checkSuppliersLogin(String supplierId) {
        int status = 1;
         Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction trans = session.beginTransaction();
         Query q = session.createQuery("from Suppliers where supplierId= :supplierId").setString("supplierId", supplierId);			        
         List userList = q.list();
         for(Iterator it = userList.iterator(); it.hasNext();){
         Suppliers odr =(Suppliers)it.next();

         if(odr.getSupplierId().equals(supplierId)){                 
             return status=0;
            }             
         }              
         trans.commit();
         return status;
    }
    
    
     public static void main(String order[]){
        SuppliersDaoImpl daoImp = new SuppliersDaoImpl();
        Suppliers s = new Suppliers();
        Users u  = new Users();
        u.setEmail("adeola@gmail.com");
//      
        String createdDate =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()); 
            
//        s.setUsers(u);
//        s.setCompanyName("cookies food");
//        s.setCreatedDate(createdDate);
//        s.setSupplierEmail("chi.live.com");
//        s.setSupplierId("SU300");
//        s.setSupplierName("Mr David");
//        s.setSupplierPhone("08135352759");
//        daoImp.createdSuppliers(s);
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
//        o.setCreatedDate(createdDate);
//        //o.setCustomers(c);
//        o.setOrderId("OR100");
//        o.setOrderName("Chocolates");
//        o.setOrderQuantity(5);
//        o.setOrderTotalPrice(1000.0);
//        o.setOrderUnitPrice(200.0);      
//        daoImp.createdOrders(o);
            
////////getEmployeeByID
//////        System.out.println(daoImp.listEmployeeByID("oal@gmail.com"));
//////        System.out.println(daoImp.checkEmployeeLogin("oal@gmail.com"));
//////        
//        //update
//      u.setEmail("favour@gmail.com");               
//        u.setFirstName("tyu");
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

