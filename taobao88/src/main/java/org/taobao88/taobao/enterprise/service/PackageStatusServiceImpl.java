package org.taobao88.taobao.enterprise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.taobao88.taobao.enterprise.DAO.PackageStatusDAO;
import org.taobao88.taobao.enterprise.entity.PackageStatus;

/**
 * Created by User on 19.06.14.
 */
@Repository("packageStatusService")
public class PackageStatusServiceImpl implements PackageStatusService{

    @Autowired
    PackageStatusDAO packageStatusDAO;


    @Override
    @Transactional
    public int savePackageStatus(PackageStatus packageStatus) {
        packageStatusDAO.savePackageStatus(packageStatus);
        return packageStatusDAO.getIdPackageStatus(packageStatus);
    }

    @Override
    @Transactional
    public int getIdPackageStatus(PackageStatus packageStatus) {
        return packageStatusDAO.getIdPackageStatus(packageStatus);
    }

    @Override
    @Transactional
    public PackageStatus findPackageStatusById(int id) {
        return packageStatusDAO.findPackageStatusById(id);
    }

    @Override
    @Transactional
    public void updatePackageStatus(PackageStatus packageStatus) {
        packageStatusDAO.updatePackageStatus(packageStatus);
    }

    @Override
    @Transactional
    public void deletePackageStatus(int id) {
        packageStatusDAO.deletePackageStatus(id);
    }
}
