package org.taobao88.taobao.enterprise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.taobao88.taobao.enterprise.dao.PackageDAO;
import org.taobao88.taobao.enterprise.entity.OrderT;
import org.taobao88.taobao.enterprise.entity.PackageT;
import org.taobao88.taobao.enterprise.entity.UserT;
import org.taobao88.taobao.enterprise.service.PackageService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 15.06.14.
 */
@Repository("packageService")
public class PackageServiceImpl implements PackageService{

    @Autowired
    PackageDAO packageDAO;

    @Override
    @Transactional
    public int addPackage(PackageT packageT) {
        return packageDAO.addPackage(packageT);
    }

    @Override
    @Transactional
    public PackageT findPackageById(int id) {
        return packageDAO.findPackageById(id);
    }

    @Override
    @Transactional
    public List<PackageT> getPackagesForAdmin() {
        return packageDAO.getPackagesForAdmin();
    }

    @Override
    @Transactional
    public void deletePackage(int id) {
        packageDAO.deletePackage(id);
    }

    @Override
    @Transactional
    public void updatePackage(PackageT packageT) {
        packageDAO.updatePackage(packageT);
    }

    @Override
    @Transactional
    public ArrayList<OrderT> getPackagesForUser(int idUser) {
        return packageDAO.getPackagesForUser(idUser);
    }

    @Override
    @Transactional
    public List<PackageT> getPackagesForAdminHistory() {
        return packageDAO.getPackagesForAdminHistory();
    }

    @Override
    @Transactional
    public ArrayList<OrderT> getPackagesForUserHistory(int idUser) {
        return packageDAO.getPackagesForUserHistory(idUser);
    }

	@Override
	public List<PackageT> getPackagesByUser(UserT user) {
		return packageDAO.getPackagesByUser(user);
	}
}
