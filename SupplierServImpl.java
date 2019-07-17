/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chi.service;

import chi.dao.SuppliersDao;
import chi.pojo.Suppliers;
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
@Service("supplierServ")
@Transactional(readOnly = true)
public class SupplierServImpl implements SupplierServ{
    @Autowired
     SuppliersDao suppliersDao;

    public SuppliersDao getSuppliersDao() {
        return suppliersDao;
    }

    public void setSuppliersDao(SuppliersDao suppliersDao) {
        this.suppliersDao = suppliersDao;
    }  
     @Override
    public void createdSuppliers(Suppliers s) {
        suppliersDao.createdSuppliers(s);
    }

    @Override
    public void updateSuppliers(Suppliers s) {
        suppliersDao.updateSuppliers(s);
    }

    @Override
    public void deleteSuppliers(Suppliers s) {
        suppliersDao.deleteSuppliers(s);
    }

    @Override
    public List<Suppliers> listSuppliersByID(String supplierId) {
        return suppliersDao.listSuppliersByID(supplierId);
    }

    @Override
    public List<Suppliers> listSuppliers() {
        return suppliersDao.listSuppliers();
    }

    @Override
    public int checkSuppliersLogin(String supplierId) {
        return suppliersDao.checkSuppliersLogin(supplierId);
    }
    
}
