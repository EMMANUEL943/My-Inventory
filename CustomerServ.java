/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chi.service;

import chi.pojo.Customers;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface CustomerServ {
    
    public void createdCustomers(Customers c);
    public void updateCustomers(Customers c);
    public void deleteCustomers(Customers c);
    public List<Customers> listCustomersByID(String customerId);    
    public List<Customers> listCustomers(); 
    public int checkCustomersLogin(String customer_id);
    
}
