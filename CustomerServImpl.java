/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chi.service;

import chi.dao.CustomerDao;
import chi.pojo.Customers;
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
@Service("customerServ")
@Transactional(readOnly = true)
public class CustomerServImpl implements CustomerServ{
    
     @Autowired
     CustomerDao customerDao;

    public CustomerDao getCustomerDao() {
        return customerDao;
    }

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }
     @Override
    public void createdCustomers(Customers c) {
        customerDao.createdCustomers(c);
    }

    @Override
    public void updateCustomers(Customers c) {
        customerDao.updateCustomers(c);
    }

    @Override
    public void deleteCustomers(Customers c) {
        customerDao.deleteCustomers(c);
    }

    @Override
    public List<Customers> listCustomersByID(String customerId) {
        return customerDao.listCustomersByID(customerId);
    }

    @Override
    public List<Customers> listCustomers() {
        return customerDao.listCustomers();
    }

    @Override
    public int checkCustomersLogin(String customer_id) {
        return customerDao.checkCustomersLogin(customer_id);
    }
    
}