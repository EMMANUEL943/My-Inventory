/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chi.service;

import chi.dao.ProductDao;
import chi.pojo.Product;
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
@Service("productServ")
@Transactional(readOnly = true)
public class ProductServImpl implements ProductServ{
      @Autowired
      ProductDao productDao;

    public ProductDao getProductDao() {
        return productDao;
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }
     @Override
    public void createdProduct(Product p) {
        productDao.createdProduct(p);
    }

    @Override
    public void updateProduct(Product p) {
        productDao.updateProduct(p);
    }

    @Override
    public void deleteProduct(Product p) {
        productDao.deleteProduct(p);
    }

    @Override
    public List<Product> listProductByID(String productId) {
        return productDao.listProductByID(productId);
    }

    @Override
    public List<Product> listProduct() {
        return productDao.listProduct();
    }

    @Override
    public int checkProductLogin(String productId) {
        return productDao.checkProductLogin(productId);
    }
    
}

