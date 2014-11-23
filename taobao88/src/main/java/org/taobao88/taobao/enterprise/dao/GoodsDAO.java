package org.taobao88.taobao.enterprise.dao;

import org.taobao88.taobao.enterprise.entity.Goods;
import org.taobao88.taobao.enterprise.entity.OrderT;

import java.util.List;

/**
 * Created by User on 01.06.14.
 */
public interface GoodsDAO {

    void persistEmployee(Goods employee);

    Goods findEmployeeById(int id);

    void updateEmployee(Goods employee);

    void deleteGood(int id);

    int saveGoods(Goods goods);

    List<Goods> getAll(List<OrderT> orderTs);

}
