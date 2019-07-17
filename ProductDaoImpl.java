/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chi.dao;


import chi.pojo.Product;
import java.util.ArrayList;
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
@Repository("productDao")
public class ProductDaoImpl implements ProductDao{
    
  private SessionFactory sessionFactory;

    @Autowired        
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public void createdProduct(Product p) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        
        //p.setSuppliers(p.getSuppliers());
        p.setExpireDate(p.getExpireDate());
        p.setManufacturedDate(p.getManufacturedDate());
        p.setNafdacNumber(p.getNafdacNumber());
        p.setProductId(p.getProductId());
        p.setProductName(p.getProductName());
        p.setQuantity(p.getQuantity());
        p.setTotalPrice(p.getTotalPrice());
        p.setUnitPrice(p.getUnitPrice());
        p.setCreatedDate(p.getCreatedDate());               
        session.save(p);
        trans.commit();
    }

    @Override
    public void updateProduct(Product p) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        
        p.setSuppliers(p.getSuppliers());
        p.setExpireDate(p.getExpireDate());
        p.setManufacturedDate(p.getManufacturedDate());
        p.setNafdacNumber(p.getNafdacNumber());
        p.setProductId(p.getProductId());
        p.setProductName(p.getProductName());
        p.setQuantity(p.getQuantity());
        p.setSuppliers(p.getSuppliers());
        p.setTotalPrice(p.getTotalPrice());
        p.setUnitPrice(p.getUnitPrice());
        p.setCreatedDate(p.getCreatedDate());
                
        session.update(p);
        trans.commit();
    }

    @Override
    public void deleteProduct(Product p) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();        
        
        p.setSuppliers(p.getSuppliers());
        p.setExpireDate(p.getExpireDate());
        p.setManufacturedDate(p.getManufacturedDate());
        p.setNafdacNumber(p.getNafdacNumber());
        p.setProductId(p.getProductId());
        p.setProductName(p.getProductName());
        p.setQuantity(p.getQuantity());
        p.setSuppliers(p.getSuppliers());
        p.setTotalPrice(p.getTotalPrice());
        p.setUnitPrice(p.getUnitPrice());
        p.setCreatedDate(p.getCreatedDate());
        
        session.delete(p);                         
        trans.commit();
    }

    @Override
    public List<Product> listProductByID(String productId) {
        Product eObj = new Product();
        List<Product> eList = new ArrayList<Product>();
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        Query queryObj = session.createQuery("from Product where productId = :productId").setString("productId", productId);
        eObj = (Product)queryObj.uniqueResult();
        eList = queryObj.list();
//        for(Product em: eList){
////            System.out.println(em.getProductId());
////            System.out.println(em.getFirstName());            
//        }
        trans.commit();
        return eList;
    }

    @Override
    public List<Product> listProduct() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        List<Product> listProduct = session.createQuery("select p from Product p").list();
        
        trans.commit();
        return listProduct;
    }

    @Override
    public int checkProductLogin(String productId) {
        int status = 1;
         Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction trans = session.beginTransaction();
         Query q = session.createQuery("from Product where productId= :productId").setString("productId", productId);			        
         List listProduct = q.list();
         for(Iterator it = listProduct.iterator(); it.hasNext();){
         Product pd =(Product)it.next();
           
         if(pd.getProductId().equals(productId)){                 
             return status=0;
            }             
         }              
         trans.commit();
         return status;
    }
    
    
//     public static void main(String product[]){
//        ProductDaoImpl daoImp = new ProductDaoImpl();
//        Product p = new Product();
//        Suppliers s = new Suppliers();
//        s.setSupplierId("SU100");
////
//        String createdDate =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()); 
//
////////
//
////insert
//        p.setExpireDate("20/5/2020");
//        p.setManufacturedDate("20/5/2017");
//        p.setNafdacNumber(011);
//        p.setProductId("P0045");
//        p.setProductName("EGG");
//        p.setQuantity(20);
//        p.setSuppliers(s);
//        p.setTotalPrice(20 * 4.0);
//        p.setUnitPrice(4.0);
//        p.setCreatedDate(p.getCreatedDate());
//        daoImp.createdProduct(p);
////




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
//        u.setEmail("adeola@gmail.com");               
//        u.setFirstName("ade");
//        u.setLastName("ola");
//        u.setPasswords("123");        
//        u.setPhone("08099901122");                
//        u.setPosition("Store Manager");                
//        u.setCreatedDate(createdDate);
//       daoImp.updateUsers(u);
////        
//////        //delete        
        //u.setEmail("buchi@gmail.com");
       // daoImp.deleteUsers(u);
//////        
//////        //getAllEmployeesDetail
//////        List<Employees> liste = daoImp.listEmployee();
//////        for(Iterator it = liste.iterator(); it.hasNext();){
//////            Employees emp = (Employees) it.next();
//////            System.out.println(emp.getEmail()+ " : "+emp.getFirstName()+ " : "+emp.getLastName());           
//////        }
//////     
    }

