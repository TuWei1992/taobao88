package org.taobao88.taobao.enterprise.service.impl;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.After;
import org.junit.Test;

public class PriceServiceImplTest {

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		double val = 0.19;
		double res = new BigDecimal(val).setScale(1, RoundingMode.UP).doubleValue();
		
		val = 1.1;
		double res2 = new BigDecimal(val).setScale(0, RoundingMode.UP).doubleValue();
		System.out.println(res);
		System.out.println(res2);
	}

}
