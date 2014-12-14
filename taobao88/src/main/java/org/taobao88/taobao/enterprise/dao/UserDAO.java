package org.taobao88.taobao.enterprise.dao;

import org.taobao88.taobao.enterprise.entity.UserT;

import java.util.List;

/**
 * Created by User on 02.06.14.
 */
public interface UserDAO {

    public void saveNewUser(UserT user);

    public List<UserT> getId(String name);

    public UserT findUserById(int id);

    public void updateUser(UserT userT);

    public List<UserT> getAll();
    
    public UserT findUserByCredentials(String login, String password);

}
