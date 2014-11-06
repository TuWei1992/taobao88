package org.taobao88.taobao.enterprise.DAO;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.taobao88.taobao.enterprise.entity.PageContent;

@Repository("pagesContentDAO")
@Transactional
public class PagesContentDAOImpl implements PagesContentDAO {

	@Autowired private SessionFactory sessionFactory;
	
	@Override
	public int addPageContent(PageContent content) {
		return (int) sessionFactory.getCurrentSession().save(content);
	}

	@Override
	public void deletePageContent(PageContent content) {
		sessionFactory.getCurrentSession().delete(content);
	}

	@Override
	public void updatePageContent(PageContent content) {
		sessionFactory.getCurrentSession().update(content);
	}

	@Override
	public PageContent findContentByPageName(String page) {
		return (PageContent) sessionFactory.getCurrentSession().createQuery("from PageContent where page = :page").setParameter("page", page).uniqueResult();
	}

}
