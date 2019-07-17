/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chi.dao;

import chi.pojo.Customers;
import chi.pojo.Orders;
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
@Repository("orderDao")
public class OrderDaoImpl implements OrderDao{
     private SessionFactory sessionFactory;

    @Autowired        
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public void createdOrders(Orders o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        o.setCustomers(o.getCustomers());
        o.setCreatedDate(o.getCreatedDate());
        o.setCustomers(o.getCustomers());
        o.setOrderId(o.getOrderId());
        o.setOrderName(o.getOrderName());
        o.setOrderQuantity(o.getOrderQuantity());
        o.setOrderTotalPrice(o.getOrderTotalPrice());
        o.setOrderUnitPrice(o.getOrderUnitPrice());             
        session.save(o);
        trans.commit();
    }

    @Override
    public void updateOrders(Orders o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        
        o.setCreatedDate(o.getCreatedDate());
        o.setCustomers(o.getCustomers());
        o.setCustomers(o.getCustomers());
        o.setOrderId(o.getOrderId());
        o.setOrderName(o.getOrderName());
        o.setOrderQuantity(o.getOrderQuantity());
        o.setOrderTotalPrice(o.getOrderTotalPrice());
        o.setOrderUnitPrice(o.getOrderUnitPrice());             
        session.update(o);
        trans.commit();
    }

    @Override
    public void deleteOrders(Orders o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();        
        
       o.setCreatedDate(o.getCreatedDate());
       o.setCustomers(o.getCustomers());
        o.setCustomers(o.getCustomers());
        o.setOrderId(o.getOrderId());
        o.setOrderName(o.getOrderName());
        o.setOrderQuantity(o.getOrderQuantity());
        o.setOrderTotalPrice(o.getOrderTotalPrice());
        o.setOrderUnitPrice(o.getOrderUnitPrice());             
        session.delete(o);
        trans.commit();
    }

    @Override
    public List<Orders> listOrdersByID(String orderId) {
        Orders eObj = new Orders();
        List<Orders> eList = new ArrayList<Orders>();
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        Query queryObj = session.createQuery("from Orders where orderId = :orderId").setString("orderId", orderId);
        eObj = (Orders)queryObj.uniqueResult();
        eList = queryObj.list();
//        for(Employees em: eList){
////            System.out.println(em.getEmail());
////            System.out.println(em.getFirstName());            
//        }
        trans.commit();
        return eList;
    }

    @Override
    public List<Orders> listOrders() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        List<Orders> listOrders = session.createQuery("select o from Orders o").list();
        
        trans.commit();
        return listOrders;
    }

    @Override
    public int checkOrdersLogin(String order_id) {
        int status = 1;
         Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction trans = session.beginTransaction();
         Query q = session.createQuery("from Orders where orderId= :orderId").setString("orderId", order_id);			        
         List userList = q.list();
         for(Iterator it = userList.iterator(); it.hasNext();){
         Orders odr =(Orders)it.next();

         if(odr.getOrderId().equals(order_id)){                 
             return status=0;
            }             
         }              
         trans.commit();
         return status;
    }
    
    
     public static void main(String order[]){
        OrderDaoImpl daoImp = new OrderDaoImpl();
        Orders o = new Orders();
        Customers c = new Customers();
        c.setCustomerId("CS001");
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
//        o.setCreatedDate(createdDate);
//        o.setCustomers(c);
//        o.setOrderId("OR1001");
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
        //getAllEmployeesDetail
        List<Orders> liste = daoImp.listOrders();
        for(Iterator it = liste.iterator(); it.hasNext();){
            Orders r = (Orders) it.next();
            System.out.println(r.getOrderId()+ " : "+r.getOrderName()+ " : "+r.getOrderQuantity());           
        }
     
    }

}

