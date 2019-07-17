/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chi.service;

import chi.pojo.Product;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface ProductServ {
    
    public void createdProduct(Product p);
    public void updateProduct(Product p);
    public void deleteProduct(Product p);
    public List<Product> listProductByID(String productId);    
    public List<Product> listProduct(); 
    public int checkProductLogin(String productId);
    
}
