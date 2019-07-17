/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chi.dao;

import chi.pojo.Users;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface UserDao {
    
     public void createdUsers(Users u);
    public void updateUsers(Users u);
    public void deleteUsers(Users u);
    public List<Users> listUsersByID(String emailId);    
    public List<Users> listUsers(); 
    public int checkUsersLogin(String email_id); 
}
