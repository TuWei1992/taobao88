package org.taobao88.taobao.enterprise.service.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.taobao88.taobao.enterprise.entity.SideMenu;
import org.taobao88.taobao.enterprise.service.SideMenuService;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations = {"classpath:root-context.xml"})
@Transactional
public class SideMenuServiceImplTest {

	@Autowired private SideMenuService sideMenuService;
	
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		List<SideMenu> menus = sideMenuService.getSideMenuForPage(1, "parent_id");
		assertTrue(menus.size() != 0);
		assertTrue(menus.get(0).getLevel() == 0);
		
		menus = sideMenuService.getSideMenuForPage(2, "parent_id");
		assertTrue(menus.size() != 0);
		assertTrue(menus.get(0).getLevel() == 1);
	}

}
