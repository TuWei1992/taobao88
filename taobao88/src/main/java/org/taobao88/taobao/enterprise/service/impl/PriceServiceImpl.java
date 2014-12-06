package org.taobao88.taobao.enterprise.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.taobao88.taobao.enterprise.dao.GoodsDAO;
import org.taobao88.taobao.enterprise.entity.Goods;
import org.taobao88.taobao.enterprise.entity.OrderT;
import org.taobao88.taobao.enterprise.entity.PostService;
import org.taobao88.taobao.enterprise.entity.PostServicePrice;
import org.taobao88.taobao.enterprise.entity.UserT;
import org.taobao88.taobao.enterprise.service.PriceService;

@Repository("priceService")
public class PriceServiceImpl implements PriceService {
	
	@Autowired private GoodsDAO goodsDAO;

	@Override
	public double getPriceOfOrder(int amount, double priceForOne) {
		double price = 0;
        price = (priceForOne + 10) * 1.1 * 0.19;
        price *= amount;
        price = new BigDecimal(price).setScale(2, RoundingMode.UP).doubleValue();
        return price;
	}

	@Override
	public double getFullPriceOfOrder(UserT user, double priceOrder, double weight, int amount) {
		double price = 0;
        if(Integer.parseInt(user.getCountryUser()) == 248) {
            price = priceOrder + getValueForBelarus((weight / 1000) * amount);
        } if(Integer.parseInt(user.getCountryUser()) == 3159) {
            price = priceOrder + getValueForRussia((weight / 1000) * amount);
        } if(Integer.parseInt(user.getCountryUser()) == 9908) {
            price = priceOrder + getValueForUkraine((weight / 1000) * amount);
        }

        return new BigDecimal(price).setScale(0, RoundingMode.UP).doubleValue();
	}
	
	private double getValueForBelarus(double weight){
        double D = 0;
        double Weight = 0;

        if(weight < 1000){
            Weight = new BigDecimal(weight).setScale(1, RoundingMode.UP).doubleValue();
        }else{
            Weight = new BigDecimal(weight).setScale(0, RoundingMode.UP).doubleValue();
        }

        Map<Double,Integer> belarus = new HashMap<Double,Integer>();

        belarus.put(0.1,18);
        belarus.put(0.2,33);
        belarus.put(0.3,48);
        belarus.put(0.4,63);
        belarus.put(0.5,78);
        belarus.put(0.6,93);
        belarus.put(0.7,108);
        belarus.put(0.8,123);
        belarus.put(1.0,155);
        belarus.put(2.0,211);
        belarus.put(3.0,267);
        belarus.put(4.0,324);
        belarus.put(5.0,380);
        belarus.put(6.0,436);
        belarus.put(7.0,493);
        belarus.put(8.0,549);
        belarus.put(9.0,605);
        belarus.put(10.0,662);
        belarus.put(11.0,718);
        belarus.put(12.0,774);
        belarus.put(13.0,830);
        belarus.put(14.0,887);
        belarus.put(15.0,943);
        belarus.put(16.0,1003);
        belarus.put(17.0,1063);
        belarus.put(18.0,1123);
        belarus.put(19.0,1183);
        belarus.put(20.0,1243);

        for(Map.Entry<Double,Integer> entry : belarus.entrySet()){
            Double key = entry.getKey();
            if(key == (Weight)){
                D = entry.getValue();
                break;
            }
        }

        return D*0.19;
    }
	
	private double getValueForRussia(double weight){
        double D = 0;
        double Weight = 0;

        if(weight < 1000){
            Weight = new BigDecimal(weight).setScale(1, RoundingMode.UP).doubleValue();
        }else{
            Weight = new BigDecimal(weight).setScale(0, RoundingMode.UP).doubleValue();
        }

        Map<Double,Integer> belarus = new HashMap<Double,Integer>();

        belarus.put(0.1,38);
        belarus.put(0.2,53);
        belarus.put(0.3,68);
        belarus.put(0.4,83);
        belarus.put(0.5,98);
        belarus.put(0.6,113);
        belarus.put(0.7,128);
        belarus.put(0.8,143);
        belarus.put(1.0,191);
        belarus.put(2.0,250);
        belarus.put(3.0,309);
        belarus.put(4.0,368);
        belarus.put(5.0,427);
        belarus.put(6.0,487);
        belarus.put(7.0,546);
        belarus.put(8.0,606);
        belarus.put(9.0,665);
        belarus.put(10.0,723);
        belarus.put(11.0,783);
        belarus.put(12.0,843);
        belarus.put(13.0,902);
        belarus.put(14.0,961);
        belarus.put(15.0,1021);
        belarus.put(16.0,1079);
        belarus.put(17.0,1139);
        belarus.put(18.0,1199);
        belarus.put(19.0,1258);
        belarus.put(20.0,1317);

        for(Map.Entry<Double,Integer> entry : belarus.entrySet()){
            Double key = entry.getKey();
            if(key == (Weight)){
                D = entry.getValue();
                break;
            }
        }

        return D*0.19;
    }
	
	private double getValueForUkraine(double weight){
        double D = 0;
        double Weight = 0;

        if(weight < 1000){
            Weight = new BigDecimal(weight).setScale(1, RoundingMode.UP).doubleValue();
        }else{
            Weight = new BigDecimal(weight).setScale(0, RoundingMode.UP).doubleValue();
        }

        Map<Double,Integer> belarus = new HashMap<Double,Integer>();

        belarus.put(0.1,38);
        belarus.put(0.2,53);
        belarus.put(0.3,68);
        belarus.put(0.4,83);
        belarus.put(0.5,98);
        belarus.put(0.6,113);
        belarus.put(0.7,128);
        belarus.put(0.8,143);
        belarus.put(1.0,181);
        belarus.put(2.0,235);
        belarus.put(3.0,289);
        belarus.put(4.0,343);
        belarus.put(5.0,397);
        belarus.put(6.0,451);
        belarus.put(7.0,505);
        belarus.put(8.0,559);
        belarus.put(9.0,614);
        belarus.put(10.0,668);
        belarus.put(11.0,722);
        belarus.put(12.0,776);
        belarus.put(13.0,830);
        belarus.put(14.0,885);
        belarus.put(15.0,939);
        belarus.put(16.0,993);
        belarus.put(17.0,1047);
        belarus.put(18.0,1101);
        belarus.put(19.0,1155);
        belarus.put(20.0,1209);

        for(Map.Entry<Double,Integer> entry : belarus.entrySet()){
            Double key = entry.getKey();
            if(key == (Weight)){
                D = entry.getValue();
                break;
            }
        }

        return D*0.19;
    }

	@Override
	public int getOrderPrice(OrderT order) {
		Goods goods = goodsDAO.findEmployeeById(order.getIdGoods());
		double price = 0;
		if (goods.getPhotoGoods().equals("true")) {
			price = ((goods.getPriceGoods() + 10 + 15) * 1.1 * 0.19) * goods.getAmountGoods();
		} else if (goods.getPhotoGoods().equals("false")) {
			price = ((goods.getPriceGoods() + 10) * 1.1 * 0.19) * goods.getAmountGoods();
		}
		price = new BigDecimal(price).setScale(0, RoundingMode.UP).doubleValue();
		price = Math.floor(price);
		return (int) price;
	}

	@Override
	public int getDeliveryPrice(List<OrderT> orders, UserT user, double priceWithoutDelivery, PostService postService) {
		double totalWeight = 0;
		double totalPrice = priceWithoutDelivery;
		for (OrderT o : orders) {
			totalWeight += o.getGoods().getWeightGoods() * o.getGoods().getAmountGoods();
		}
		totalWeight = totalWeight / 1000;
		if (totalWeight < 1.0) {
			totalWeight = new BigDecimal(totalWeight).setScale(1, RoundingMode.UP).doubleValue();
		} else {
			totalWeight = new BigDecimal(totalWeight).setScale(0, RoundingMode.UP).doubleValue();
		}
		
		boolean found = false;
		for (PostServicePrice postPrice : postService.getPostServicesPrices()) {
			if (totalWeight == postPrice.getWeight()) {
				totalPrice += postPrice.getPrice() * 0.19;
				found = true;
			}
		}
		if (!found) {
			return 0;
		} else {	
			totalPrice = Math.floor(totalPrice);
			return (int) totalPrice;
		}
	}
	
	

}
