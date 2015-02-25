package org.taobao88.taobao.enterprise.service;

import org.taobao88.taobao.enterprise.entity.PackageStatus;

/**
 * Created by User on 19.06.14.
 */
public interface PackageStatusService {

    int savePackageStatus(PackageStatus packageStatus);

    int getIdPackageStatus(PackageStatus packageStatus);

    PackageStatus findPackageStatusById(int id);

    void updatePackageStatus(PackageStatus packageStatus);

    void deletePackageStatus(int id);
}
