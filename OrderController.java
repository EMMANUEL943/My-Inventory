/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chi.controller;
import chi.pojo.Customers;
import chi.pojo.Orders;
import chi.service.OrderServ;
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
@Named(value = "orderController")
@RequestScoped
public class OrderController implements Serializable{
 @ManagedProperty(value="#{oredrServ}") 

    @Autowired
    private OrderServ orderServ;

    public OrderServ getOrderServ() {
        return orderServ;
    }

    public void setOrderServ(OrderServ orderServ) {
        this.orderServ = orderServ;
    }

     private String orderId;
     private Customers customers;
     private String orderName;
     private Double orderUnitPrice;
     private Integer orderQuantity;
     private Double orderTotalPrice;
     private String createdDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
     private String mgsError;
     private String cusId;

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Double getOrderUnitPrice() {
        return orderUnitPrice;
    }

    public void setOrderUnitPrice(Double orderUnitPrice) {
        this.orderUnitPrice = orderUnitPrice;
    }

    public Integer getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(Integer orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public Double getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(Double orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
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
   
    public OrderController() {
    }
     public String saveOrder(){         
        Orders o = new Orders(); 
        Customers c = new Customers();
       c.setCustomerId(cusId);
        
        o.setCreatedDate(createdDate);
        o.setCustomers(c);
        o.setOrderId(orderId);
        o.setOrderName(orderName);
        o.setOrderQuantity(orderQuantity);
        o.setOrderTotalPrice(orderTotalPrice);
        o.setOrderUnitPrice(orderUnitPrice);
        
      /* try{                    
        int message =0;         
        if(message !=0){     
            orderServ.createdOrders(o);
            setFieldToNull();            
            mgsError="Order Record Created!!!";
            
        }else if(message == 0){  
           
             mgsError="User Record Already Exist!!!";
             setFieldToNull();           
        }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return "/orders.xhtml"; 
    }
        */
        orderServ.createdOrders(o);
        setFieldToNull();
             mgsError="Order Record Created!!!";

            return "orders.xhtml";
     }

     public String orderUpdate(){
       Orders o = new Orders(); 
        Customers c = new Customers();
         c.setCustomerId(cusId);
        //Employees e = new Employees(empEmail); 
        o.setCreatedDate(createdDate);
        o.setCustomers(c);
        o.setOrderId(orderId);
        o.setOrderName(orderName);
        o.setOrderQuantity(orderQuantity);
        o.setOrderTotalPrice(orderTotalPrice);
        o.setOrderUnitPrice(orderUnitPrice);
                
        orderServ.updateOrders(o);
        setFieldToNull();
        mgsError="Order Record Updated!!!";

        return "ordersEdit.xhtml";
    } 
     public String orderDelete(){
       Orders o = new Orders(); 
        Customers c = new Customers();
         c.setCustomerId(cusId);
        //Employees e = new Employees(empEmail); 
        o.setCreatedDate(createdDate);
        o.setCustomers(c);
        o.setOrderId(orderId);
        o.setOrderName(orderName);
        o.setOrderQuantity(orderQuantity);
        o.setOrderTotalPrice(orderTotalPrice);
        o.setOrderUnitPrice(orderUnitPrice);
                
        orderServ.deleteOrders(o);
        setFieldToNull();
        mgsError="Order Record Deleted!!!";

        return "ordersEdit.xhtml";
    } 
     public String getOrderDetailsByID() {
       Orders o = new Orders(); 
        Customers c = new Customers();
       try{                                 
        List<Orders> sList = orderServ.listOrdersByID(orderId);
//                System.out.println(supplierId);
            int checkOrdersLogin = orderServ.checkOrdersLogin(orderId);
                if(checkOrdersLogin==0){                   
		for(Iterator it = sList.iterator(); it.hasNext();) {                    
                    o = (Orders)it.next();  
                    orderId = o.getOrderId();
                    cusId = o.getCustomers().getCustomerId();
                    orderName = o.getOrderName();
                    orderQuantity =o.getOrderQuantity();
                    createdDate = o.getCreatedDate();
                    orderTotalPrice =o.getOrderTotalPrice();
                    orderUnitPrice = o.getOrderUnitPrice();
                
        }
             }else if(checkOrdersLogin!=0){
               setMgsError("Order Record does not Exist!!");                                                          
           } 
       }catch(Exception ex){
           ex.printStackTrace();
       }
		return "ordersEdit";
    }
     public List<Orders> getOrderDetails() {            
        Orders o = new Orders();
        Customers c = new Customers();
        
        List<Orders> sList = orderServ.listOrders();
            for(Iterator it = sList.iterator();it.hasNext();) {                    
                o = (Orders)it.next();
                    orderId = o.getOrderId();
                    //cusId = o.getCustomers().getCustomerId();
                    orderName = o.getOrderName();
                    orderQuantity =o.getOrderQuantity();
                    createdDate = o.getCreatedDate();
                    orderTotalPrice =o.getOrderTotalPrice();
                    orderUnitPrice = o.getOrderUnitPrice();
                     
            }
            return sList;
        }
      public void setFieldToNull(){
        this.setCreatedDate("");
        this.setCusId("");
        this.setCustomers(null);
        this.setOrderId("");
        this.setOrderName("");
        this.setMgsError("");
        this.setOrderQuantity(0);
        this.setOrderTotalPrice(0.0);
        this.setOrderUnitPrice(0.0);
}
}
