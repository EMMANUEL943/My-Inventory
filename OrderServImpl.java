/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chi.service;

import chi.dao.OrderDao;
import chi.pojo.Orders;
import java.util.List;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Named
@Service("orderServ")
@Transactional(readOnly = true)
public class OrderServImpl implements OrderServ{
     @Autowired
     OrderDao orderDao;

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    
     @Override
    public void createdOrders(Orders o) {
        orderDao.createdOrders(o);
    }

    @Override
    public void updateOrders(Orders o) {
        orderDao.updateOrders(o);
    }

    @Override
    public void deleteOrders(Orders o) {
        orderDao.deleteOrders(o);
    }

    @Override
    public List<Orders> listOrdersByID(String orderId) {
        return orderDao.listOrdersByID(orderId);
    }

    @Override
    public List<Orders> listOrders() {
        return orderDao.listOrders();
    }

    @Override
    public int checkOrdersLogin(String orderId) {
        return orderDao.checkOrdersLogin(orderId);
    }
    
}

