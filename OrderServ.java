/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chi.service;

import chi.pojo.Orders;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface OrderServ {
    public void createdOrders(Orders o);
    public void updateOrders(Orders c);
    public void deleteOrders(Orders c);
    public List<Orders> listOrdersByID(String orderId);    
    public List<Orders> listOrders(); 
    public int checkOrdersLogin(String orderId);
    
}
