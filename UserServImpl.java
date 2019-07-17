/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chi.service;

import chi.dao.UserDao;
import chi.pojo.Users;
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
@Service("userServ")
@Transactional(readOnly = true)
public class UserServImpl implements UserServ {
    
    @Autowired
    UserDao userDao; 

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    @Override
    public void createdUsers(Users u) {
        userDao.createdUsers(u);
    }

    @Override
    public void updateUsers(Users u) {
        userDao.updateUsers(u);
    }

    @Override
    public void deleteUsers(Users u) {
        userDao.deleteUsers(u);
    }

    @Override
    public List<Users> listUsersByID(String emailId) {
        return userDao.listUsersByID(emailId);
    }

    @Override
    public List<Users> listUsers() {
        return userDao.listUsers();
    }

    @Override
    public int checkUsersLogin(String email_id) {
        return userDao.checkUsersLogin(email_id);
    }
    
}

