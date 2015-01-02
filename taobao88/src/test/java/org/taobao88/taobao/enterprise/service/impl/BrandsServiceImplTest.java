package org.taobao88.taobao.enterprise.service.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.taobao88.taobao.enterprise.dao.BrandsDAO;
import org.taobao88.taobao.enterprise.entity.Brands;
import org.taobao88.taobao.enterprise.service.BrandsService;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations = {"classpath:root-context.xml"})
//@Transactional
public class BrandsServiceImplTest {

	@Autowired private BrandsService brandsService;
	@Autowired private BrandsDAO brandsDAO;
	
	@After
	public void tearDown() throws Exception {
	}

	@Test
	@Ignore
	public void test() {
		List<Brands> brands = null;
		brands = brandsService.getBrandsPartial(1);
		assertTrue(brands.size() != 0);
		assertTrue(brands.size() == 50);
		
		brands = brandsService.getBrandsPartial(2);
		assertTrue(brands.size() != 0);
		assertTrue(brands.size() == 50);
		
		brands = brandsService.getBrandsPartial(3);
		assertTrue(brands.size() != 0);
		assertTrue(brands.size() == 50);
	}
}
