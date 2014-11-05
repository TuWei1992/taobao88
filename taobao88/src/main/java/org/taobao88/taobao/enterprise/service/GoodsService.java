package org.taobao88.taobao.enterprise.service;

import org.taobao88.taobao.enterprise.entity.Goods;
import org.taobao88.taobao.enterprise.entity.OrderT;
import org.taobao88.taobao.enterprise.entity.Recomendation;

import java.util.List;

/**
 * Created by User on 01.06.14.
 */
public interface GoodsService {

    public void persistEmployee(Goods employee);

    public Goods findEmployeeById(int id);

    public void updateEmployee(Goods employee);

    public void deleteGood(int id);

    public List<Goods> getAll(List<OrderT> orderTs);

    public int saveGoods(Goods goods);
    
    public Goods convertFromRecomendationToGoods(Recomendation recomendation);

}
