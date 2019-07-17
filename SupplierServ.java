/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chi.service;


import chi.pojo.Suppliers;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface SupplierServ {
    public void createdSuppliers(Suppliers s);
    public void updateSuppliers(Suppliers s);
    public void deleteSuppliers(Suppliers s);
    public List<Suppliers> listSuppliersByID(String supplierId);    
    public List<Suppliers> listSuppliers(); 
    public int checkSuppliersLogin(String supplierId);
    
    
}
