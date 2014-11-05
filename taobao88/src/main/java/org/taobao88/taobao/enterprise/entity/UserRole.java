package org.taobao88.taobao.enterprise.entity;

import javax.persistence.*;

/**
 * Created by User on 03.06.14.
 */
@Entity
@Table(name="user_roles")
public class UserRole {

    @Id
    @GeneratedValue
    @Column(name = "user_role_id", nullable = false)
    private int idRoleUser;

    @Column(name = "authority")
    private String authority;

    @Column(name = "user_id")
    private int idUser;

    public int getIdRoleUser() {
        return idRoleUser;
    }

    public void setIdRoleUser(int idRoleUser) {
        this.idRoleUser = idRoleUser;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRole userRole = (UserRole) o;

        if (idRoleUser != userRole.idRoleUser) return false;
        if (idUser != userRole.idUser) return false;
        if (!authority.equals(userRole.authority)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRoleUser;
        result = 31 * result + authority.hashCode();
        result = 31 * result + idUser;
        return result;
    }
}
