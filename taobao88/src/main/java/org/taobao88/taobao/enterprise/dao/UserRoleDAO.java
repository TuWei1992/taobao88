package org.taobao88.taobao.enterprise.dao;

import org.taobao88.taobao.enterprise.entity.UserRole;
import org.taobao88.taobao.enterprise.entity.UserT;

/**
 * Created by User on 03.06.14.
 */
public interface UserRoleDAO {

    public void save(UserRole userRole);
    
    public UserRole findRole(UserT user);

}
