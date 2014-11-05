package org.taobao88.taobao.enterprise.DAO;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.taobao88.taobao.enterprise.entity.OrderT;
import org.taobao88.taobao.enterprise.entity.PackageT;
import org.taobao88.taobao.enterprise.entity.UserT;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 15.06.14.
 */
@Transactional
@Repository("packageDAO")
public class PackageDAOImpl implements PackageDAO{

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public int addPackage(PackageT packageT) {
        sessionFactory.getCurrentSession().save(packageT);
        return (Integer) sessionFactory.getCurrentSession().getIdentifier(packageT);
    }

    @Override
    public PackageT findPackageById(int id) {
        return (PackageT)sessionFactory.getCurrentSession().get(PackageT.class,id);
    }

    @Override
    public List<PackageT> getPackagesForAdmin() {
        String query = "";
        query  = "from PackageT where approve='false' ORDER BY idPackage DESC ";
        List<PackageT> packageTs = sessionFactory.getCurrentSession().createQuery(query).list();
        for(int i=0;i<packageTs.size();i++) {
            System.out.println(packageTs);
        }
        return (List<PackageT>) sessionFactory.getCurrentSession().createQuery(query).list();
    }

    @Override
    public List<PackageT> getPackagesForAdminHistory() {
        String query = "";
        query  = "from PackageT where approve='true' ORDER BY datePackage desc";

        return (List<PackageT>) sessionFactory.getCurrentSession().createQuery(query).list();
    }

    @Override
    public void deletePackage(int id) {
        sessionFactory.getCurrentSession().delete(findPackageById(id));
    }

    @Override
    public void updatePackage(PackageT packageT) {
        sessionFactory.getCurrentSession().update(packageT);
    }

    @Override
    public ArrayList<OrderT> getPackagesForUser(int idUser) {
        String query = "select idPackage from OrderT where (approve='false' AND idUser="+idUser+")";

        System.out.println(query);

        return (ArrayList<OrderT>) sessionFactory.getCurrentSession().createQuery(query).list();
    }
    
    @Override
    public List<PackageT> getPackagesByUser(UserT user) {
    	return sessionFactory.getCurrentSession().createQuery("FROM PackageT P, OrderT O WHERE P.approve = 'false' AND P.idPackage = O.packageT.idPackage AND O.idUser = :user_id").setParameter("user_id", user.getIdUser()).list();
    }

    @Override
    public ArrayList<OrderT> getPackagesForUserHistory(int idUser) {
        String query = "select idPackage from OrderT where (approve='true' AND idUser="+idUser+")";

        return (ArrayList<OrderT>) sessionFactory.getCurrentSession().createQuery(query).list();
    }
}
