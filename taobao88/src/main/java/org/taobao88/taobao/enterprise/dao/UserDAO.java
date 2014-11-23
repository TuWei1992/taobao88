package org.taobao88.taobao.enterprise.dao;

import org.taobao88.taobao.enterprise.entity.UserT;

import java.util.List;

/**
 * Created by User on 02.06.14.
 */
public interface UserDAO {

    void saveNewUser(UserT user);

    List<UserT> getId(String name);

    UserT findUserById(int id);

    void updateUser(UserT userT);

    List<UserT> getAll();

}
