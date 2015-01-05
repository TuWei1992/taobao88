package org.taobao88.taobao.enterprise.dao;

import org.taobao88.taobao.enterprise.entity.Goods;
import org.taobao88.taobao.enterprise.entity.OrderT;

import java.util.List;

/**
 * Created by User on 01.06.14.
 */
public interface GoodsDAO {

	public void persistEmployee(Goods employee);

    public Goods findEmployeeById(int id);

    public void updateEmployee(Goods employee);

    public void deleteGood(int id);

    public int saveGoods(Goods goods);

    public List<Goods> getAll(List<OrderT> orderTs);
    
    public void delete(Goods goods);

}
