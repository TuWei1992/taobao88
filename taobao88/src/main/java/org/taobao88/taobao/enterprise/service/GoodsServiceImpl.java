package org.taobao88.taobao.enterprise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.taobao88.taobao.enterprise.DAO.GoodsDAO;
import org.taobao88.taobao.enterprise.entity.Goods;
import org.taobao88.taobao.enterprise.entity.OrderT;
import org.taobao88.taobao.enterprise.entity.Recomendation;

import java.util.List;

/**
 * Created by User on 01.06.14.
 */

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService{

    @Autowired private GoodsDAO goodsDAO;
    @Autowired private PriceService priceService;
    
    @Override
    @Transactional
    public void persistEmployee(Goods goods) {
        goodsDAO.persistEmployee(goods);
    }

    @Override
    @Transactional
    public Goods findEmployeeById(int id) {
        return goodsDAO.findEmployeeById(id);
    }

    @Override
    @Transactional
    public void updateEmployee(Goods goods) {
        goodsDAO.updateEmployee(goods);
    }

    @Override
    @Transactional
    public void deleteGood(int id) {
        goodsDAO.deleteGood(id);
    }

    @Override
    @Transactional
    public List<Goods> getAll(List<OrderT> orderTs) {
        return goodsDAO.getAll(orderTs);
    }

    @Override
    @Transactional
    public int saveGoods(Goods goods) {
        return goodsDAO.saveGoods(goods);
    }

	@Override
	public Goods convertFromRecomendationToGoods(Recomendation recomendation) {
		Goods good = new Goods();
		good.setHrefGoods(recomendation.getHref());
		good.setAmountGoods(1);
		good.setWeightGoods(recomendation.getWeight());
		good.setPriceGoods(priceService.getPriceOfOrder(good.getAmountGoods(), recomendation.getPrice()));
		good.setNameGoods(recomendation.getDescription());
		good.setChinaGoods(null);
		good.setPhotoGoods("true");
		good.setPhoto(recomendation.getPhoto());
		good.setRecomendation(recomendation);
		return good;
	}
}
