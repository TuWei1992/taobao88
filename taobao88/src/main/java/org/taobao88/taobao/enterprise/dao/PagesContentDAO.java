package org.taobao88.taobao.enterprise.dao;

import org.taobao88.taobao.enterprise.entity.PageContent;

public interface PagesContentDAO {

	public PageContent findContentByPageName(String page);
	
	public int addPageContent(PageContent content);
	
	public void deletePageContent(PageContent content);
	
	public void updatePageContent(PageContent content);
	
}
