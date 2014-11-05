package org.taobao88.taobao.enterprise.service;

import org.taobao88.taobao.enterprise.entity.OrderT;
import org.taobao88.taobao.enterprise.entity.PackageT;
import org.taobao88.taobao.enterprise.entity.UserT;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 15.06.14.
 */
public interface PackageService {

    int addPackage(PackageT packageT);

    PackageT findPackageById(int id);

    List<PackageT> getPackagesForAdmin();

    void deletePackage(int id);

    void updatePackage(PackageT packageT);

    ArrayList<OrderT> getPackagesForUser(int idUser);

    List<PackageT> getPackagesForAdminHistory();

    ArrayList<OrderT> getPackagesForUserHistory(int idUser);
    
    List<PackageT> getPackagesByUser(UserT user);

}
