package org.taobao88.taobao.enterprise.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.taobao88.taobao.enterprise.dao.GoodsDAO;
import org.taobao88.taobao.enterprise.entity.Goods;
import org.taobao88.taobao.enterprise.entity.OrderT;

import java.util.List;

/**
 * Created by User on 01.06.14.
 */
@Transactional
@Repository("goodsDAO")
public class GoodsDAOImpl implements GoodsDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void persistEmployee(Goods goods) {
        sessionFactory.getCurrentSession().persist(goods);
    }

    @Override
    public Goods findEmployeeById(int id) {
        return (Goods) sessionFactory.getCurrentSession().get(Goods.class, id);
    }

    @Override
    public void updateEmployee(Goods goods) {
        sessionFactory.getCurrentSession().update(goods);
    }

    @Override
    public void deleteGood(int id) {
        Goods goods = findEmployeeById(id);
        sessionFactory.getCurrentSession().delete(goods);
    }

    @Override
    public int saveGoods(Goods goods) {
        sessionFactory.getCurrentSession().save(goods);
        return (Integer) sessionFactory.getCurrentSession().getIdentifier(goods);
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<Goods> getAll(List<OrderT> orderTs) {

        if(orderTs.size()!=0){
        String query = "from Goods where ";

        for(int i=0;i<orderTs.size();i++){
            if(i<orderTs.size()-1){
                query+="idGoods='"+orderTs.get(i).getIdGoods()+"' OR ";
            }else{
                query+="idGoods='"+orderTs.get(i).getIdGoods()+"'";
            }
        }

        return sessionFactory.getCurrentSession().createQuery(query).list();
        }

        return null;
    }

	@Override
	public void delete(Goods goods) {
		sessionFactory.getCurrentSession().delete(goods);		
	}

}
